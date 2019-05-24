// variables
var successResponseCode = 1;
var tokenKey = "token";
var currentIdChatKey = "currentChat";
var currentUserNicknameKey = "currentNicknameUser";
var currentUserIdKey = "currentIdUser";
var lastMessageDateInCurrentChat = "lastMessageDateInCurrentChat";
var firstMessageDateInCurrentChat = "firstMessageDateInCurrentChat";
var currentHistoryTimer = "currentHistoryTimer";
var currentChatsTimer = "currentChatsTimer";

// forms
var activeChat = "<div id=\"chat-_\" class=\"chat_list active_chat\">\n" +
    "                            <div class=\"chat_people\">\n" +
    "                                <div class=\"chat_img\"><img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"></div>\n" +
    "                                <div class=\"chat_ib\">\n" +
    "                                    <h5 id=\"title-chat-_\"><span id=\"date-chat-_\" class=\"chat_date\"></span></h5>\n" +
    "                                    <p id=\"message-chat-_\"></p>\n" +
    "                                </div>\n" +
    "                            </div>\n" +
    "                        </div>";

var chat = "<div id=\"chat-_\" class=\"chat_list\" onclick=\"onChooseActiveChat(this)\">\n" +
    "            <div class=\"chat_people\">\n" +
    "                <div class=\"chat_img\"><img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"></div>\n" +
    "                <div class=\"chat_ib\">\n" +
    "                    <h5 id=\"title-chat-_\"><span id=\"date-chat-_\" class=\"chat_date\"></span></h5>\n" +
    "                    <p id=\"last-message-chat-_\"></p>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>";

var incomingMessage = "<div id=\"message-_\" class=\"incoming_msg\">\n" +
    "                        <div class=\"incoming_msg_img\"><img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\">\n" +
    "                        </div>\n" +
    "                        <div class=\"received_msg\">\n" +
    "                            <div class=\"received_withd_msg\">\n" +
    "                                <p id=\"text-message-_\"></p>\n" +
    "                                <span id=\"info-message-_\" class=\"time_date\"></span>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                    </div>";

var outgoingMessage = "<div id=\"message-_\" class=\"outgoing_msg\">\n" +
    "                        <div class=\"sent_msg\">\n" +
    "                            <p id=\"text-message-_\"></p>\n" +
    "                            <span id=\"info-message-_\" class=\"time_date\"></span>\n" +
    "                        </div>\n" +
    "                    </div>";

var chatFrom = "<div class=\"container\">\n" +
    "            <h3 class=\"text-center\">Messaging</h3>\n" +
    "            <div class=\"messaging\">\n" +
    "                <div class=\"inbox_people\">\n" +
    "                    <div class=\"headind_srch\">\n" +
    "                        <div class=\"recent_heading\">\n" +
    "                            <h4 id=\"nickname\"></h4>\n" +
    "                        </div>\n" +
    "                        <div class=\"srch_bar\">\n" +
    "                             <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#exampleModal\">\n" +
    "                               Create chat\n" +
    "                             </button>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div id=\"chats\" class=\"inbox_chat\">\n" +
    "\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "                <div class=\"mesgs\">\n" +
    "                    <div id=\"messages\" class=\"msg_history\">\n" +
    "\n" +
    "                    </div>\n" +
    "                    <div class=\"type_msg\">\n" +
    "                        <div class=\"input_msg_write\">\n" +
    "                            <input id=\"new_message\" type=\"text\" class=\"write_msg\" placeholder=\"Type a message\" onkeydown=\"onPressEnter(event)\"/>\n" +
    "                            <button class=\"msg_send_btn\" type=\"button\" onclick=\"onSendMessage()\"><i class=\"fa fa-paper-plane-o\" aria-hidden=\"true\"></i>\n" +
    "                            </button>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "<div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n" +
    "        <div class=\"modal-dialog\" role=\"document\">\n" +
    "            <div class=\"modal-content\">\n" +
    "                <div class=\"modal-body\">\n" +
    "                    <div class=\"container\">\n" +
    "                        <div class=\"row\">\n" +
    "                            <h2>Create new chat</h2>\n" +
    "                        </div>\n" +
    "                        <form>\n" +
    "                            <div class=\"form-group\">\n" +
    "                                <label for=\"title\">Title</label>\n" +
    "                                <input id=\"titleForNewChat\" type=\"text\" class=\"form-control\" aria-describedby=\"emailHelp\" placeholder=\"Enter title of chat\">\n" +
    "                            </div>\n" +
    "                            <div class=\"form-group\">\n" +
    "                                <label for=\"filterWord\">Search users</label>\n" +
    "                                <input id=\"filterWord\" type=\"search\" class=\"form-control\" aria-describedby=\"emailHelp\" placeholder=\"Enter three or more letters for searching\" onkeyup=\"onSearchUsers()\">\n" +
    "                            </div>\n" +
    "                            <div class=\"form-group\">\n" +
    "                                <label for=\"users\">Choose users</label>\n" +
    "                                <ul id=\"foundUsers\" class=\"list-group\">\n" +
    "                                </ul>\n" +
    "                            </div>\n" +
    "                            <button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"onCreateNewChat()\">Create chat</button>\n" +
    "                        </form>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>\n";

var foundUserInList = "<li id=\"user-_\" class=\"list-group-item\" onclick=\"onChooseUser(this)\"></li>";

var startPage = "<div id=\"start\" class=\"row fadeInDown\">\n" +
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

    // console.log(uri);

    sendJsonRequest(requestType, uri, requestData, completeFunction);

    return false;
}

function onLoginUser() {
    var requestData = createAndFillLoginRequestObject();
    var completeFunction = onLoginResponse;
    var requestType = "POST";
    var uri = "user/login";

    // console.log(uri);
    // console.log(requestData);

    sendJsonRequest(requestType, uri, requestData, completeFunction);

    return false;
}

function onSuccessfulLogin() {
    var requestData = null;
    var completeFunction = onChatFromResponse;
    var requestType = "GET";
    var uri = "chat";
    var authHeader = window.sessionStorage.getItem(tokenKey);

    sendAuthJsonRequest(requestType, uri, requestData, completeFunction, authHeader);
}

function onChooseActiveChat(currentElement) {
    $(".active_chat").attr("onclick", "onChooseActiveChat(this)");
    $(".active_chat").removeClass("active_chat");


    $("#" + currentElement.id).addClass("active_chat");
    $("#" + currentElement.id).removeAttr("onclick");

    var requestData = null;
    var completeFunction = onChatInfoResponse;
    var requestType = "GET";

    // console.log(event);

    var idChat = currentElement.id.replace("chat-", "");

    // console.log(idChat);

    var uri = ("chat/" + idChat);
    var authHeader = window.sessionStorage.getItem(tokenKey);

    sendAuthJsonRequest(requestType, uri, requestData, completeFunction, authHeader);
}

function onSendMessage() {
    var requestData = createAndFillNewMessageRequestObject();
    var completeFunction = onSendMessageResponse;
    var requestType = "POST";
    var uri = ("chat/" + window.sessionStorage.getItem(currentIdChatKey) + "/message");
    var authHeader = window.sessionStorage.getItem(tokenKey);

    // console.log(requestData);

    sendAuthJsonRequest(requestType, uri, requestData, completeFunction, authHeader);
}

function onPressEnter(event) {
    if (event.keyCode === 13) {
        onSendMessage();
    }
}

function onUpdateHistory() {
    var requestData = null;
    var completeFunction = onUpdateHistoryResponse;
    var requestType = "GET";
    var uri = ("chat/" + window.sessionStorage.getItem(currentIdChatKey) + "/message?from=" + window.sessionStorage.getItem(firstMessageDateInCurrentChat));
    var authHeader = window.sessionStorage.getItem(tokenKey);

    // console.log("Date: " + window.sessionStorage.getItem(firstMessageDateInCurrentChat));
    console.log(uri);

    sendAuthJsonRequest(requestType, uri, requestData, completeFunction, authHeader);
}

function onCreateNewChat() {
    var requestData = createAndFillNewChatRequestObject();
    var completeFunction = onCreateNewChatResponse;
    var requestType = "POST";
    var uri = "chat";
    var authHeader = window.sessionStorage.getItem(tokenKey);

    sendAuthJsonRequest(requestType, uri, requestData, completeFunction, authHeader);
}

function onChooseUser(currentUser) {
    if ($("#" + currentUser.id).is(".active")) {
        $("#" + currentUser.id).removeClass("active");
    } else {
        $("#" + currentUser.id).addClass("active");
    }
}

function onSearchUsers() {
    var filterWord = $("#filterWord").val();

    if (filterWord.length > 2) {
        var requestData = null;
        var completeFunction = onUpdateUsersList;
        var requestType = "GET";
        var uri = ("user?filterWord=" + filterWord);

        // console.log("Date: " + window.sessionStorage.getItem(firstMessageDateInCurrentChat));

        sendJsonRequest(requestType, uri, requestData, completeFunction);
    }
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

    // console.log(requestData.password);

    return requestData;
}

function createAndFillLoginRequestObject() {
    var requestData = {
        login: $("#login").val(),
        password: $("#password").val()
    };

    return requestData;
}

function createAndFillNewMessageRequestObject() {
    var requestData = {
        text: $("#new_message").val()
    };

    // console.log(requestData.text);

    return requestData;
}

function createAndFillNewChatRequestObject() {
    var usersIds = [];

    console.log($("li").filter(".active"));

    $("li").filter(".active").each(function (index, value) {
        usersIds.push(parseInt(value.id.toString().replace("user-", "")));
    })

    var requestData = {
        title: $("#titleForNewChat").val(),
        participantsIds: usersIds
    };

    return requestData;
}

// response callback handlers
function onRegisterResponse(xmlRq) {
    // console.log(xmlRq.status);

    // console.log(xmlRq.responseText);

    var responseJson = $.parseJSON(xmlRq.responseText);

    onCallStartPage();

    if (responseJson.status.code == successResponseCode) {
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

    window.sessionStorage.setItem(tokenKey, token);
    window.sessionStorage.setItem(currentUserNicknameKey, responseJson.user.nickname);
    window.sessionStorage.setItem(currentUserIdKey, responseJson.user.id);

    // console.log(token);

    // console.log(window.sessionStorage.getItem(tokenKey));
    // console.log(window.sessionStorage.getItem(currentUserNicknameKey));


    onCallStartPage();

    console.log(responseJson.status.code);

    if (responseJson.status.code == successResponseCode) {
        console.log(responseJson.status.code);
        onSuccessfulLogin();
    } else {
        onFail(responseJson.status.message);
    }
}

function onChatFromResponse(xmlRq) {
    // console.log(xmlRq.status);

    // console.log(xmlRq.responseText);

    var responseJson = $.parseJSON(xmlRq.responseText);
    console.log(responseJson.status.code);

    if (responseJson.status.code == successResponseCode) {
        // console.log(responseJson.chats);

        $("#main").html(chatFrom);

        // console.log(window.sessionStorage.getItem(currentUserNicknameKey));

        $("#nickname").html("Chats for " + window.sessionStorage.getItem(currentUserNicknameKey));

        fillChatForm(responseJson.chats);

    } else if(responseJson.status.code == 0) {
        $("#main").html(chatFrom);

        // console.log(window.sessionStorage.getItem(currentUserNicknameKey));

        $("#nickname").html("Chats for " + window.sessionStorage.getItem(currentUserNicknameKey));
    } else {
        onFail(responseJson.status.message);
    }
}

function onChatInfoResponse(xmlRq) {
    // console.log(xmlRq.status);

    // console.log(xmlRq.responseText);

    var responseJson = $.parseJSON(xmlRq.responseText);

    if (responseJson.status.code == successResponseCode) {
        // console.log(responseJson);

        fillHistory(responseJson);

        scrollDown($("#messages"));
    } else {
        onFail(responseJson.status.message);
    }
}

function onSendMessageResponse(xmlRq) {
    var responseJson = $.parseJSON(xmlRq.responseText);

    if (responseJson.status.code == successResponseCode) {
        $("#new_message").val("");

        fillNewMessage(responseJson.message);

        scrollDown($("#messages"));
    } else {
        onFail(responseJson.status.message);
    }
}

function onUpdateHistoryResponse(xmlRq) {
    var responseJson = $.parseJSON(xmlRq.responseText);

    if (responseJson.status.code == successResponseCode) {
        var isNew = updateHistory(responseJson.history);

        if (isNew) {
            scrollDown($("#messages"));
        }
    } else if(responseJson.status.code == 0){

    } else {
        clearInterval(window.sessionStorage.getItem(currentHistoryTimer));

        onFail(responseJson.status.message);
    }
}

function onUpdateUsersList(xmlRq) {
    var responseJson = $.parseJSON(xmlRq.responseText);

    if (responseJson.status.code == successResponseCode) {
        var list = $("#foundUsers");

        var users = list.children(".list-group-item");

        $.each(users, function (index, value) {
            if (!$("#" + value.id).is(".active")) {
                $("#" + value.id).remove();

                // delete $("#" + value.id);
            }
        });

        fillListOfUsers(responseJson.users);
    } else {
        onFail(responseJson.status.message);
    }
}

function onCreateNewChatResponse(xmlRq) {
    var responseJson = $.parseJSON(xmlRq.responseText);

    console.log(responseJson);

    if (responseJson.status.code == successResponseCode) {
        var chatItem = chat;
        // console.log(chatItem);

        chatItem = chatItem.replace("\"chat-_\"", ("\"chat-" + responseJson.chat.id + "\""));
        chatItem = chatItem.replace("\"title-chat-_\"", ("\"title-chat-" + responseJson.chat.id + "\""));
        chatItem = chatItem.replace("\"date-chat-_\"", ("\"date-chat-" + responseJson.chat.id + "\""));
        chatItem = chatItem.replace("\"last-message-chat-_\"", ("\"last-message-chat-" + responseJson.chat.id + "\""));

        // console.log(chatItem);

        $("#chats").prepend(chatItem);

        $("#title-chat-" + responseJson.chat.id).html(responseJson.chat.title);
    }
}

function onFail(textStatus) {
    onCallStartPage();
    $("#start").prepend(errorAlert);
    $("#alert").html(textStatus);
}

// form fillets
function fillChatForm(chats) {
    $.each(chats, function () {
        var chatItem = chat;
        // console.log(chatItem);

        chatItem = chatItem.replace("\"chat-_\"", ("\"chat-" + this.id + "\""));
        chatItem = chatItem.replace("\"title-chat-_\"", ("\"title-chat-" + this.id + "\""));
        chatItem = chatItem.replace("\"date-chat-_\"", ("\"date-chat-" + this.id + "\""));
        chatItem = chatItem.replace("\"last-message-chat-_\"", ("\"last-message-chat-" + this.id + "\""));

        // console.log(chatItem);

        $("#chats").append(chatItem);

        $("#title-chat-" + this.id).html(this.title);
        // $("#date-chat-" + this.id).html(this.lastMessage.creationDate);

        // $("#last-message-chat-" + this.id).html(this.lastMessage);
    });
}

function fillHistory(chatInfo) {
    console.log(chatInfo);
    window.sessionStorage.setItem(currentIdChatKey, chatInfo.chat.id);

    // console.log(window.sessionStorage.getItem(currentIdChatKey));

    $("#messages").html("");

    if (chatInfo.lastHistory != null && chatInfo.lastHistory != undefined) {
        $.each(chatInfo.lastHistory, function (index, value) {
            // console.log("Index: " + index);

            fillNewMessage(value);

            if (index == chatInfo.lastHistory.length - 1) {
                window.sessionStorage.setItem(lastMessageDateInCurrentChat, value.dateTimeOfCreating);
            } else if (index == 0) {
                window.sessionStorage.setItem(firstMessageDateInCurrentChat, value.dateTimeOfCreating);
            }
        })
    } else {
        var currentDate = new Date();

        var currentDateString = currentDate.toISOString().replace(/\.[a-z,A-Z,0-9]*/, "");

        window.sessionStorage.setItem(firstMessageDateInCurrentChat, currentDateString);
    }

    var timer = setInterval(updateHistoryTimer, 3000);

    var oldtimer = window.sessionStorage.getItem(currentHistoryTimer);

    if (oldtimer != null && oldtimer != undefined) {
        clearInterval(window.sessionStorage.getItem(currentHistoryTimer));
    }

    window.sessionStorage.setItem(currentHistoryTimer, timer);
}

function updateHistory(history) {
    var isNew = false;

    // console.log(history);

    if (history != null && history != undefined) {
        $.each(history, function (index, value) {
            // console.log($("#message-" + value.id));

            if ($("div").is("#message-" + value.id)) {
                // // console.log(value);

                if (value.dateTimeOfEditing != null && value.dateTimeOfEditing != undefined) {
                    var oldDateTimeOfEditing = $("#info-message-" + value.id).html.toString().replace("Created ", "").replace("Edited ", "").replace((" | by " + value.author.nickname), "");

                    if (oldDateTimeOfEditing != value.dateTimeOfEditing) {
                        $("#text-message-" + value.id).html(value.text);
                        $("#info-message-" + value.id).html("Updated " + value.dateTimeOfEditing + " | by " + value.author.nickname);
                    }
                }
            } else {
                // console.log(value);

                fillNewMessage(value);

                if (index == history.length - 1) {
                    window.sessionStorage.setItem(lastMessageDateInCurrentChat, value.dateTimeOfCreating);
                    isNew = true;
                }
            }
        })
    }

    return isNew;
}

function fillListOfUsers(users) {
    if (users != null && users != undefined) {
        $.each(users, function (index, value) {
            if (!$("li").is("#user-" + value.id)) {
                var newElement = foundUserInList;

                newElement = newElement.replace("\"user-_\"", ("\"user-" + value.id + "\""));

                $("#foundUsers").append(newElement);

                $("#user-" + value.id).html(value.nickname);
            }
        });
    }
}

function startPage() {

}

// timers functions

function updateHistoryTimer() {
    // console.log("Timer: " + window.sessionStorage.getItem(currentHistoryTimer));

    onUpdateHistory();
}

// common functions

function fillNewMessage(message) {
    var messageItem;

    if (message.author.id == window.sessionStorage.getItem(currentUserIdKey)) {
        messageItem = outgoingMessage;
    } else {
        messageItem = incomingMessage;
    }

    messageItem = messageItem.replace("\"message-_", ("\"message-" + message.id + "\""));
    messageItem = messageItem.replace("\"text-message-_\"", ("\"text-message-" + message.id + "\""));
    messageItem = messageItem.replace("\"info-message-_\"", ("\"info-message-" + message.id + "\""));

    $("#messages").append(messageItem);

    $("#text-message-" + message.id).html(message.text);

    if (message.dateTimeOfEditing == null || message.dateTimeOfEditing == undefined) {
        // console.log(message.dateTimeOfCreating);

        $("#info-message-" + message.id).html("Created " + message.dateTimeOfCreating + " | by " + message.author.nickname);
    } else {
        // console.log(message.dateTimeOfEditing);

        $("#info-message-" + message.id).html("Updated " + message.dateTimeOfEditing + " | by " + message.author.nickname);
    }
}

function scrollDown(element) {
    var height = element.prop("scrollHeight");

    // console.log(height);

    element.animate({scrollTop: height}, "slow");
}

// sending requests
function sendJsonRequest(requestType, uri, requestData, completeFunction) {
    var url = ("https://localhost:8443/" + uri);
    // console.log(url);
    // var body = JSON.stringify(requestData);

    var xmlRq = new XMLHttpRequest();
    xmlRq.open(requestType, url, true);
    xmlRq.setRequestHeader("content-type", "application/json");
    xmlRq.setRequestHeader("cache-control", "no-cache");
    // xmlRq.withCredentials = true;
    // xmlRq.setRequestHeader("Access-Control-Allow-Origin", "*");
    // xmlRq.setRequestHeader("Access-Control-Allow-Credentials", "true");
    xmlRq.onload = function (ev) {
        completeFunction(xmlRq);
    };

    if (requestData == undefined || requestData == null) {
        xmlRq.send();
    } else {
        var body = JSON.stringify(requestData);
        xmlRq.send(body);
    }

    // xmlRq.send(body);


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
    // // console.log(settings.url);
    //
    // $.ajax(settings);
}


function sendAuthJsonRequest(requestType, uri, requestData, completeFunction, authHeader) {
    var url = ("https://localhost:8443/" + uri);
    // console.log(url);

    var xmlRq = new XMLHttpRequest();
    xmlRq.open(requestType, url, true);
    xmlRq.setRequestHeader("Authorization", authHeader);
    xmlRq.setRequestHeader("content-type", "application/json");
    xmlRq.setRequestHeader("cache-control", "no-cache");
    // xmlRq.setRequestHeader("Access-Control-Allow-Origin", "*");
    // xmlRq.setRequestHeader("Access-Control-Allow-Credentials", "true");
    xmlRq.onload = function (ev) {
        completeFunction(xmlRq);
    };

    if (requestData == undefined || requestData == null) {
        xmlRq.send();
    } else {
        var body = JSON.stringify(requestData);
        xmlRq.send(body);
    }
}

function sendFormDataRequest(requestType, uri, requestData, completeFunction) {
    var url = ("https://localhost:8443/" + uri);
    // console.log(url);
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

    // console.log(requestData);

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
    // // console.log(settings.url);
    //
    // $.ajax(settings);
}