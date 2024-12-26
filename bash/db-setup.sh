sudo apt update -y

sudo apt install -y mysql-server

sudo sed -i "s/^bind-address.*/bind-address = 0.0.0.0/" /etc/mysql/mysql.conf.d/mysqld.cnf
sudo sed -i "s/^mysqlx-bind-address.*/mysqlx-bind-address = 0.0.0.0/" /etc/mysql/mysql.conf.d/mysqld.cnf


sudo mysql -ppassword -e "CREATE USER 'java-app'@'%' IDENTIFIED BY 'password'; GRANT ALL PRIVILEGES ON library.* TO 'java-app'@'%'; FLUSH PRIVILEGES;"

sudo systemctl restart mysql

curl -o /home/ubuntu/dbsetup.sql https://raw.githubusercontent.com/KianDoyle/calorie-tracker/refs/heads/main/dbsetup.sql

sudo mysql < /home/ubuntu/dbsetup.sql