#!/usr/bin/env bash

Bold='\033[1m'   # Bold
NC='\033[0m'     # No Color

# To change this banner, go to http://patorjk.com/software/taag/#p=display&f=Big&t=Paperclip%0ACompany
cat << "EOF"

  _____                          _ _            
 |  __ \                        | (_)           
 | |__) |_ _ _ __   ___ _ __ ___| |_ _ __       
 |  ___/ _` | '_ \ / _ \ '__/ __| | | '_ \      
 | |  | (_| | |_) |  __/ | | (__| | | |_) |     
 |_|___\__,_| .__/ \___|_|  \___|_|_| .__/      
  / ____|   | |                     | |         
 | |     ___|_| __ ___  _ __   __ _ |_|_  _   _ 
 | |    / _ \| '_ ` _ \| '_ \ / _` | '_ \| | | |
 | |___| (_) | | | | | | |_) | (_| | | | | |_| |
  \_____\___/|_| |_| |_| .__/ \__,_|_| |_|\__, |
                       | |                 __/ |
                       |_|                |___/ 

EOF

echo -e "${Bold}Initializing schema...${NC}"
psql --host=postgres --username=postgres -w -d paperclip-company-db -f ./schema.sql &&
echo -e "\n${Bold}Initializing products...${NC}"
psql --host=postgres --username=postgres -w -d paperclip-company-db -c "\copy products(id, ean, name, description) FROM 'products.csv' DELIMITER ',' CSV HEADER"

# To change this banner, go to http://patorjk.com/software/taag/#p=display&f=Big&t=DB%20ready%20to%20use!
cat << "EOF"
  _____  ____                       _         _                         _ 
 |  __ \|  _ \                     | |       | |                       | |
 | |  | | |_) |  _ __ ___  __ _  __| |_   _  | |_ ___    _   _ ___  ___| |
 | |  | |  _ <  | '__/ _ \/ _` |/ _` | | | | | __/ _ \  | | | / __|/ _ \ |
 | |__| | |_) | | | |  __/ (_| | (_| | |_| | | || (_) | | |_| \__ \  __/_|
 |_____/|____/  |_|  \___|\__,_|\__,_|\__, |  \__\___/   \__,_|___/\___(_)
                                       __/ |                              
                                      |___/                               
EOF
