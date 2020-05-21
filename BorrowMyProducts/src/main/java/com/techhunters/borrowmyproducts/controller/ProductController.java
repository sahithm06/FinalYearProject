package com.techhunters.borrowmyproducts.controller;

import com.techhunters.borrowmyproducts.dto.*;
import com.techhunters.borrowmyproducts.entity.Product;
import com.techhunters.borrowmyproducts.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRequestService productRequestService;

    @Autowired
    private TwilioMessageApi twilioCustomMessageApi;

    @RequestMapping(value = "/myProducts", method = RequestMethod.GET)
    public String displayMyProducts(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());
        ProductLocationDTO productLocationDTO = new ProductLocationDTO();
        ProductDTO product = new ProductDTO();
        CategoryDTO category = new CategoryDTO();
        model.addAttribute("product", product);
        model.addAttribute("location", productLocationDTO);
        model.addAttribute("category", category);

        List<ProductDTO> products = productService.listByUserId(user.getUserId());
        if (products.size() ==0)
            model.addAttribute("noProducts", true);
        else {
            List<ProductDTO> aProducts = new ArrayList<>();
            List<ProductDTO> soldProducts = new ArrayList<>();
            List<ProductDTO> disabledProducts = new ArrayList<>();
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductStatus().equalsIgnoreCase("available"))
                    aProducts.add(products.get(i));

                else if (products.get(i).getProductStatus().equalsIgnoreCase("in use"))
                    soldProducts.add(products.get(i));
                else if(products.get(i).getProductStatus().equalsIgnoreCase("disable"))

                     disabledProducts.add(products.get(i));

            }
            model.addAttribute("products", aProducts);
            model.addAttribute("soldProducts", soldProducts);
            model.addAttribute("disabledProducts",disabledProducts);
        }
        return "myProducts";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id)
    {
        ProductDTO productDTO=productService.findById(id);
        List<ProductRequestDTO> requests=productRequestService.listByProduct(id);
        if(requests!=null)
        for(ProductRequestDTO req:requests)
            productRequestService.delete(req);
        productService.delete(productDTO);
        return "redirect:/myProducts";
    }

    @GetMapping("/makeAvailableOrDisable/{id}")
    public String makeAvailable(@PathVariable("id") int id)
    {
        //retrieving  and making available to other users
        ProductDTO proDto=productService.findById(id);
        List<ProductRequestDTO> productRequestDTO=productRequestService.listByProduct(id);

        //deleting all accepted requests for this product
        for(ProductRequestDTO req:productRequestDTO)
            productRequestService.delete(req);

        if(proDto.getProductStatus().equals("in use") || proDto.getProductStatus().equals("disable"))
        proDto.setProductStatus("available");
        else proDto.setProductStatus("disable");
        productService.save(proDto);
        return "redirect:/myProducts";
    }

    @PostMapping("/addProduct")
    public String addMyProduct(HttpServletRequest request, @ModelAttribute(name = "product") @Validated ProductDTO productDTO, @ModelAttribute(name = "location") @Validated ProductLocationDTO productLocationDTO, @ModelAttribute(name = "category") CategoryDTO cat, Model model, BindingResult result) throws IOException {
        log.info("Started adding product");
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());
        if (productLocationDTO.getLatitude() == null && productLocationDTO.getLongitude() == null) {
            model.addAttribute("locationError", true);
            return "myProducts";
        }
        if (result.hasErrors()) {
            return "myProducts";
        }
        CategoryDTO category = categoryService.findByCategoryName(cat.getCategoryName());
        log.info(user.getEmail());
        log.info(category.getCategoryName());
        log.info("" + productLocationDTO.getLatitude());
        productDTO.setProductLocation(productLocationDTO);
        if(productDTO.getProductName().equals("other"))
        productDTO.setProductName(productDTO.getManualProductName());
        productDTO.setProductStatus("available");
        productDTO.setImage("/images/" + productDTO.getImage());
        productLocationDTO.setProduct(productDTO);
        productDTO.setUser(user);
        productDTO.setCategory(category);
        productService.save(productDTO);
        return "redirect:/myProducts";
    }

    @GetMapping("/category/{id}")
    public String electronicProductcs(Model model, HttpServletRequest request, @PathVariable("id") String id) {
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());

        List<ProductDTO> products = productService.listAvailableProductsByCategoryLocation(id, user.getUserId());
        if (products == null)
            model.addAttribute("noProducts", true);
        model.addAttribute("products", products);
        return "categoryProducts";
    }

    @GetMapping("/requested/{category}/{id}")
    public String updateRequest(Model model, @PathVariable("id") int id, @PathVariable("category") String category, HttpServletRequest request) {
//        log.info(product);
        Principal principal = request.getUserPrincipal();
        UserDTO user = userService.findByEmail(principal.getName());
        ProductDTO product1 = productService.findById(id);
        UserDTO owner = userService.findById(product1.getUser().getUserId());
        String msg = "Your product " + product1.getProductName() + " has been requested , please login to accept the request";
        if (twilioCustomMessageApi.notifyUser(msg, owner.getPhone())) {
            log.info("owner has been notified successfully");
        } else {
            log.info("error");
        }
        ProductRequestDTO productRequest = new ProductRequestDTO();
        productRequest.setProduct(product1);
        productRequest.setUser(user);
        productRequest.setStatus("pending");
        productRequestService.save(productRequest);
        log.info("" + category);
        return "redirect:/category/{category}";
    }

    @GetMapping("/myRequests")
    public String seeRequests(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        List<ProductRequestDTO> requestDTOS = productRequestService.listByUser(principal.getName());
        if (requestDTOS.size() == 0)
            model.addAttribute("noProducts", true);
        model.addAttribute("productRequests", requestDTOS);

        return "myRequests";
    }

    @GetMapping("/seeRequests/{id}")
    public String myProductsRequests(@PathVariable("id") int id, Model model) {
        List<ProductRequestDTO> requests = productRequestService.listByProductPending(id);

        if (requests.size() == 0)
            model.addAttribute("noProducts", true);

        log.info("" + requests.size());
        model.addAttribute("productRequests", requests);
        return "seeRequests";
    }



    @GetMapping("/accepted/{proId}/{userId}")
    public String accepted(@PathVariable("proId") int proId, @PathVariable("userId") int userId, Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        UserDTO owner = userService.findByEmail(principal.getName());
        ProductDTO product1 = productService.findById(proId);
        product1.setProductStatus("in use");
        productService.save(product1);
        List<ProductRequestDTO> requests = productRequestService.listByProduct(proId);
        for (ProductRequestDTO req : requests) {
            if (req.getUser().getUserId() == userId) {

                String ownerMsg = "You have successfully accepted the request for  "
                        + product1.getProductName() + " ,your customer mobile number is: " + req.getUser().getPhone();
                if (twilioCustomMessageApi.notifyUser(ownerMsg, owner.getPhone())) {
                    log.info("owner has been notified successfully");
                } else {
                    log.info("error");
                }

                req.setStatus("accepted");
            } else {
                req.setStatus("rejected");
            }
            productRequestService.save(req);
            if (req.getUser().getUserId() != userId) {
                log.info("" + req.getUser().getPhone());
                String rejectMsg = "Your product request for the product " + product1.getProductName() + " has been rejected";
                if (twilioCustomMessageApi.notifyUser(rejectMsg, req.getUser().getPhone())) {
                    log.info("requested user  has been notified successfully");
                } else {
                    log.info("error");
                }

                productRequestService.delete(req);
            }
        }
        UserDTO user = userService.findById(userId);
        String msg = "Your product request for the product " + product1.getProductName() + " has been accepted" + ", your owner mobile number is " + owner.getPhone();
        if (twilioCustomMessageApi.notifyUser(msg, user.getPhone())) {
            log.info("user has been notified successfully");
        } else {
            log.info("error");
        }
        model.addAttribute("categories", categoryService.findAll());
        return "accepted";
    }

    @GetMapping("/reject/{proId}/{userId}")
    public String reject(@PathVariable("proId") int proId, @PathVariable("userId") int userId, Model model) {

        String productName = productService.findById(proId).getProductName();
        List<ProductRequestDTO> requests = productRequestService.listByProduct(proId);
        for (ProductRequestDTO req : requests) {
            if (req.getUser().getUserId() == userId) {

                String msg = "Your product request  for " + productName + " has been cancelled by the owner,Thank you ";
                if (twilioCustomMessageApi.notifyUser(msg, req.getUser().getPhone())) {
                    log.info("user has been notified successfully for rejection ");
                } else {
                    log.info("error in sending the rejection msg to the requested user");
                }

                productRequestService.delete(req);
            }
        }
        return "redirect:/seeRequests/{proId}";
    }
}
