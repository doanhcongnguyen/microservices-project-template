# Install Nodejs
sudo apt install -y nodejs

# Checkout GenieAcs Simulator
git clone https://github.com/zaidka/genieacs-sim.git
cd genieacs-sim
npm install

# Copy configuration file
Put data_model_H646X_v1_60mins.json & run.sh to genieacs-sim folder

# Create systemd daemon
sudo cp genieacs-sim.service /etc/systemd/system
sudo systemctl enable genieacs-sim
sudo systemctl start genieacs-sim

# Simulate
 ./genieacs-sim -u http://10.72.0.201:7547 -m data_model_H646X_v1_60mins.json -s 1 -p 26
 
 Eg:
 ssh fnf@10.72.0.202
 fnf/1
 cd genieacs-sim
 ./run.sh

# Docs
https://dzsi.sharepoint.com/:w:/s/apac-qns-div-TechandQA/ERQt9dwBMZVGpoBKzcaNKZYBSZK6qjvsIiMryEyDELGwsg?e=mUHkx5
