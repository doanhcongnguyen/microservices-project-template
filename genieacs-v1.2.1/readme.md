# Install Nodejs 12
curl -sL https://deb.nodesource.com/setup_12.x | sudo -E bash -
sudo apt install -y nodejs

# Install MongoDb
sudo apt install -y mongodb

# Install GenieAcs
sudo mkdir -p /opt/genieacs-v1.2.1/ext
sudo cp -r dist/* /opt/genieacs-v1.2.1/
sudo cp systemd/* /etc/systemd/system/

cd /opt/genieacs-v1.2.1/
sudo npm install

sudo systemctl enable genieacs-cwmp
sudo systemctl enable genieacs-nbi
sudo systemctl enable genieacs-fs
sudo systemctl enable genieacs-ui

sudo systemctl start genieacs-cwmp
sudo systemctl start genieacs-nbi
sudo systemctl start genieacs-fs
sudo systemctl start genieacs-ui --ui-jwt-secret secret

# GenieAcs UI
localhost:3000
acc/pwd: admin/admin

# Allow access GenieAcs database
sudo vi /etc/mongodb.conf
edit bind_ip 127.0.0.1 -> 0.0.0.0

# Note
Remember to create genieacs.env file
