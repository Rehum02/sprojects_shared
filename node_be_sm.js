// <!-- ======================= .... =======================+
// App Backend 1.0.0  [ v 1.0.0]
// Description : Sample ... 
// Owner       : ...
// Support     : ....
// Author_dev 	: rehum  [R] 
// Contributors: 
//                rehum [@R - k.77itc@gmail.com]   (1)
//
// (c) Copyright ... 2022  ✔ 
// ===========================================================+ -->


// ------------------- 1  - Getiing Files  -------------------
const express = require('express');
const http = require('http');
const cors = require('cors'); 
const cron = require('node-cron');
const passport = require('passport');
const socketio = require('socket.io');

const APP_CONSTANTS = require("./assets/utils-global/app.const");
const DB_CONNEXION   = require('./assets/db/db.connect');
const logger = require("./assets/utils-global/logger"); 
const BINANCE_FUNC  = require('./assets/utils-coins/bnb_func');
const app_endpub__middleware = require('./assets/middlewares/app_public.middleware');
const passport_main_middleware = require('./assets/middlewares/passport_main.middleware');
const {passport_strategy, passport_strategy____dbset} = require('./assets/middlewares/passport.strategy');
   
const  {auth__routes, auth__routes____dbset}  = require('./assets/routes/authp.routes');
const  {home_default__routes, home_default__routes____dbset} = require('./assets/routes/home_default.routes');
     

// ------------------- 2  - Defining Setup - Config   -------------------
process.env.TZ = "Africa/Abidjan"; 
const APP_ENV = APP_CONSTANTS.MODE.PROD;
const port =  APP_CONSTANTS.CONST_SERVER_PORT; 
const hostname = APP_CONSTANTS.CONST_SERVER_HOSTNAME;
const app = express();
const server = http.createServer(app);
const ioserver = socketio.listen(server, {path:'/be/socket.io'});
// ....
   
app.use(cors({origin:'*'}));       
app.use( express.json({limit:'50mb'}) );
app.disable('x-powered-by');
app.use(passport.initialize()); 
passport_strategy(passport);
// ....

// DB Connexion + ...  HERE ...
passport_strategy____dbset(DB_CONNEXION);
home_default__routes____dbset(DB_CONNEXION);



// ------------------- 3  - Routes ::    -------------------
// .... 
app.use('/be',  home_default__routes);

// ------------------- 4  - WebSocket|SocketIO  ::    ------------------
// ....
 
// ------------------- 5  - Cron  ::    -----------------


// -------------------   ::    --------------------------
server.listen();
