(ns telegrambot-lib.payments.protocol
  "Telegram Payments - protocol definitions.
   
   * https://core.telegram.org/bots/api#payments")

(defprotocol Payments
  "Bots accepting payments from users."

  (send-invoice [this content]
    "Use this method to send invoices.
     On success, the sent Message is returned.")

  (answer-shipping-query [this content]
    "If you sent an invoice requesting a shipping address
     and the parameter is_flexible was specified, the Bot API
     will send an Update with a shipping_query field to the bot.
     Use this method to reply to shipping queries.
     On success, True is returned.")

  (answer-precheckout-query [this content]
    "Once the user has confirmed their payment and shipping details,
     the Bot API sends the final confirmation in the form of an Update
     with the field pre_checkout_query.
     Use this method to respond to such pre-checkout queries.
     On success, True is returned."))
