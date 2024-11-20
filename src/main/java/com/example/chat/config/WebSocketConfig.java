package com.example.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
        /ws: The endpoint clients use to connect to the WebSocket server.
        withSockJS():
        Adds fallback support for browsers that don’t support WebSocket by using SockJS. SockJS is a library that emulates WebSocket-like behavior over HTTP.
        */
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
        Messages sent from clients to the server should have destinations prefixed with /app.
        For example, if a client sends a message to /app/chat, it will be routed to a controller's method annotated with @MessageMapping("/chat")
        */
        registry.setApplicationDestinationPrefixes("/app");

        /*
        Enables a simple in-memory message broker that clients can subscribe to.
        Messages with destinations prefixed with /topic will be broadcast to all subscribed clients.
        For example, a server can send messages to /topic/messages, and all clients subscribed to this topic will receive the messages.
        */
        registry.enableSimpleBroker("/topic");
    }
}
