
INSERT INTO category VALUES
  ('1', 'Electronic Devices', '/images/electronic.jpeg'),
  ('2', 'Home Appliances', '/images/electronic1.jpeg');

INSERT INTO USER VALUES
('1'	,'ranjith@gmail.com'	,'$2a$10$pT28Z3vhamVCSJYJ66e6x.ZNmBBtupFdw6Nh0PJ.S8yV6O/.0w1dK',
'+918247361207','ranjith','kumar');

INSERT INTO USER VALUES
('2'	,'sahith@gmail.com'	,'$2a$10$pT28Z3vhamVCSJYJ66e6x.ZNmBBtupFdw6Nh0PJ.S8yV6O/.0w1dK',
'+919492047323','sahith','rao');

INSERT INTO Address VALUES
('1' ,'123',	'hyderabad',	'India',	'12345',	'17.4118986',	'78.5707608',	'TG',	'500293',	'1');

INSERT INTO Address VALUES
('2' ,'124',	'hyderabad',	'India',	'12345',	'17.4118986',	'78.5707608',	'TG',	'500293',	'2');


INSERT INTO Product values
('1'	,'/images/laptop.jpg', '200', 'its laptop availble for rent' ,'laptop','available'	, 12, '1','1'),
 ('2'	,'/images/mixer.jpeg',	'150',  'you can make a chutneys,juices in less time....Hurry up to borrow','mixer','available', 2, '2'	,'1'),
 ('3'	,'/images/camera.jpg',	'300', 'Nikon HD utlra : model 45A00',	'Camera',	'available', 2, '1',	'1'),

 ('4'	,'/images/mouse.jpeg',	'100','Wireless Mouse',	'Mouse',	'available', 5, '1','1');

INSERT INTO Product_Location values
('1'	,'17.4118762'	,'78.57078290000001',	'1'),
 ('2',	'17.4118764',	'78.5707789',	'2'),
 ('3',	'17.4119395',	'78.5707764',	'3'),
 ('4',	'17.4119395',	'78.5707789',	'4');


