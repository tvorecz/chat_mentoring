// forms
var startPage = "<div id=\"start\" class=\"row\">\n" +
    "                <div id=\"buttons\" class=\"container\">\n" +
    "                    <div class=\"row\">\n" +
    "                        <div class=\"col-6\">\n" +
    "                            <button href=\"#\" class=\"btn btn-sq-lg btn-success\" onclick=\"onCallLoginForm()\">\n" +
    "                                Log In\n" +
    "                            </button>\n" +
    "                        </div>\n" +
    "                        <div class=\"col-6\">\n" +
    "                            <button href=\"#\" class=\"btn btn-sq-lg btn-warning\" onclick=\"onCallRegisterForm()\">\n" +
    "                                Register\n" +
    "                            </button>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>";

var loginForm = "<div id=\"formContent\">\n" +
    "                <div class=\"fadeIn first\">\n" +
    "                    <h3>Login</h3>\n" +
    "                </div>\n" +
    "                <!-- Login Form -->\n" +
    "                <form id=\"loginForm\">\n" +
    "                    <input type=\"text\" id=\"login\" class=\"fadeIn second\" name=\"login\" placeholder=\"login\">\n" +
    "                    <input type=\"text\" id=\"password\" class=\"fadeIn third\" name=\"password\" placeholder=\"password\">\n" +
    "                    <input type=\"button\" class=\"fadeIn fourth\" value=\"Log In\" onclick=\"onLoginUser()\">\n" +
    "                </form>\n" +
    "            </div>";

var registerForm = "<div id=\"formContent\">\n" +
    "                <div class=\"fadeIn first\">\n" +
    "                    <h3>Registration</h3>\n" +
    "                </div>\n" +
    "\n" +
    "                <!-- Login Form -->\n" +
    "                <form id=\"registerForm\">\n" +
    "                    <input type=\"text\" id=\"login\" class=\"fadeIn second\" name=\"login\" placeholder=\"login\">\n" +
    "                    <input type=\"text\" id=\"nickname\" class=\"fadeIn second\" name=\"nickname\" placeholder=\"nickname\">\n" +
    "                    <input type=\"text\" id=\"password\" class=\"fadeIn third\" name=\"password\" placeholder=\"password\">\n" +
    "                    <input type=\"button\" class=\"fadeIn fourth\" value=\"Register\" onclick=\"onRegisterUser()\">\n" +
    "                </form>\n" +
    "            </div>";

var chatFrom = "";

var successAlert = "<div class=\"container\">\n" +
    "            <div class=\"col-10\">\n" +
    "                <div id=\"alert\" class=\"alert alert-success text-center\" role=\"alert\">\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>";

var errorAlert = "<div class=\"container\">\n" +
    "            <div class=\"col-10\">\n" +
    "                <div id=\"alert\"  class=\"alert alert-danger text-center\" role=\"alert\">\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>";


// events
function onRegisterUser() {
    var requestData = createAndFillRegisterRequestObject();
    var completeFunction = onRegisterResponse;
    var requestType = "POST";
    var uri = "user/register";

    console.log(uri);

    sendJsonRequest(requestType, uri, requestData, completeFunction);

    return false;
}

function onLoginUser() {
    var requestData = createAndFillLoginRequestObject();
    var completeFunction = onLoginResponse;
    var requestType = "POST";
    var uri = "user/login";

    console.log(uri);
    console.log(requestData);

    sendFormDataRequest(requestType, uri, requestData, completeFunction);

    return false;
}

function onCallLoginForm() {
    $("#main").html(loginForm);
}

function onCallRegisterForm() {
    $("#main").html(registerForm);
}

function onCallStartPage() {
    $("#main").html(startPage);
}

function onCallChatPage() {
    $("#main").html("Chat");
}

// request fillers

function createAndFillRegisterRequestObject() {
    var requestData = {
        login: $("#login").val(),
        nickname: $("#nickname").val(),
        password: $("#password").val()
    };

    console.log(requestData.password);

    return requestData;
}

function createAndFillLoginRequestObject() {
    var requestData = "login=" + $("#login").val() + "&" + "password=" + $("#password").val();

    // var requestData = new FormData();
    //
    // requestData.append("login", $("#login").val());
    // requestData.append("password", $("#password").val());

    return requestData;
}

// response callback handlers
function onRegisterResponse(xmlRq) {
    console.log(xmlRq.status);

    console.log(xmlRq.responseText);

    var responseJson = $.parseJSON(xmlRq.responseText);

    onCallStartPage();

    if (responseJson.status.code == 200) {
        $("#start").prepend(successAlert);

    } else {
        $("#start").prepend(errorAlert);
    }

    $("#alert").html(responseJson.status.message);
}

function onLoginResponse(xmlRq) {
    console.log(xmlRq.status);

    console.log(xmlRq.responseText);

    var responseJson = $.parseJSON(xmlRq.responseText);

    var token = xmlRq.getResponseHeader("Authorization");

    window.sessionStorage.setItem("token", token);
    console.log(token);

    console.log(window.sessionStorage.getItem("token"));

    onCallStartPage();


    if (responseJson.status.code == 200) {
        $("#start").prepend(successAlert);
    } else {
        $("#start").prepend(errorAlert);
    }

    $("#alert").html(responseJson.status.message);
}

function onFail(jqXHR, textStatus, errorThrown) {
    console.log(errorThrown);
}

// view handlers
function startPage() {

}

// common functions

function sendJsonRequest(requestType, uri, requestData, completeFunction) {
    var url = ("https://localhost:8443/" + uri);
    console.log(url);
    var body = JSON.stringify(requestData);

    var xmlRq = new XMLHttpRequest();
    xmlRq.open(requestType, url, true);
    xmlRq.setRequestHeader("content-type", "application/json");
    xmlRq.setRequestHeader("cache-control", "no-cache");
    // xmlRq.setRequestHeader("Access-Control-Allow-Origin", "*");
    // xmlRq.setRequestHeader("Access-Control-Allow-Credentials", "true");
    xmlRq.onload = function (ev) {
        completeFunction(xmlRq);
    };

    xmlRq.send(body);


    // var settings = {
    //     method: requestType,
    //     url: ("https://localhost:8443/" + uri),
    //     data: JSON.stringify(requestData),
    //     dataType: "json",
    //     contentType: 'application/json',
    //     processData: false,
    //     success: completeFunction,
    //     headers: {
    //         "content-type": "application/json",
    //         "cache-control": "no-cache",
    //         "Access-Control-Allow-Origin": "*",
    //         "Access-Control-Expose-Headers": "x-requested-with, x-requested-by, Authorization, Origin, Content-Type"
    //     }
    // };
    //
    // console.log(settings.url);
    //
    // $.ajax(settings);
}

function sendFormDataRequest(requestType, uri, requestData, completeFunction) {
    var url = ("https://localhost:8443/" + uri);
    console.log(url);
    var xmlRq = new XMLHttpRequest();
    xmlRq.open(requestType, url, true);
    xmlRq.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xmlRq.setRequestHeader("cache-control", "no-cache");
    xmlRq.withCredentials = true;
    // xmlRq.setRequestHeader("Access-Control-Allow-Origin", "*");
    // xmlRq.setRequestHeader("Access-Control-Allow-Credentials", "true");
    // xmlRq.setRequestHeader("Access-Control-Allow-Headers", "Authorization");
    xmlRq.onload = function (ev) {
        completeFunction(xmlRq);
    };

    console.log(requestData);

    xmlRq.send(requestData);

    // var settings = {
    //     method: requestType,
    //     url: ("https://localhost:8443/" + uri),
    //     data: requestData,
    //     dataType: "json",
    //     processData: true,
    //     success: completeFunction,
    //     headers: {
    //         "content-type": "x-www-form-urlencoded",
    //         "cache-control": "no-cache",
    //         "Access-Control-Allow-Origin": "*",
    //         "Access-Control-Expose-Headers": "x-requested-with, x-requested-by, Authorization, Origin, Content-Type"
    //     }
    // };
    //
    // console.log(settings.url);
    //
    // $.ajax(settings);
}