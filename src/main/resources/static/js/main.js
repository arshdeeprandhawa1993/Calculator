'use strict';
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var usernamePage = document.querySelector('#tb01');

var stompClient = null;
var username = null;

function connect(event) {
    username = document.querySelector('#name').value.trim();
	console.log("connect");
    if(username) {
   

        var socket = new SockJS('/javatechie');
        stompClient = Stomp.over(socket);

		stompClient.connect({}, function (frame) {
		      
		        console.log('Connected: ' + frame);
		        stompClient.subscribe('/topic/public', function (Calculator) {
		            send(JSON.parse(Calculator));
		        });
		    });
		    
        }
    event.preventDefault();
}

function send(event) {
    var messageContent = messageInput.value.trim();
	console.log("send");

 	
        console.log("afterconnected")
	    if(messageContent && stompClient) {
	        var chatMessage = {
            name: messageInput.value,
            x: 1,
            y: 0,
            o: "",
            result:3
        };

        stompClient.send("/app/chat.send", {}, JSON.stringify({'claculator': $("#message").val()}));
        messageInput.value = '';
    }
    event.preventDefault();
}
usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', send, true)