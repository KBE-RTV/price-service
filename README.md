# price-service

This service calculates the prices for a list of planetary systems.

The service accepts and returns JsonStrings via rabbit-mq.

**Rabbit-MQ**

- Exchange name: "kbeTopicExchange" 
  - Queue name for calls: "priceCallQueue
    - Routing key name for calls: "priceService.call"
  - Queue name (for the receiver to listen for) for responses: "priceResponsesQueue"
    - Routing key name for responses: "priceService.response"
