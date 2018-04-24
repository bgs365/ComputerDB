  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';

  GRANT ALL PRIVILEGES ON `computer-database-db-test`.* TO 'test'@'localhost' WITH GRANT OPTION;


  FLUSH PRIVILEGES;
