(ns telegrambot-lib.payments.core
  "Telegram Payments - function implementations.
   
   * https://core.telegram.org/bots/api#payments"
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn send-invoice
  "Use this method to send invoices.
   On success, the sent Message is returned."
  [this content]
  (http/request this "sendInvoice" content))

(defn answer-shipping-query
  "If you sent an invoice requesting a shipping address
   and the parameter is_flexible was specified, the Bot API
   will send an Update with a shipping_query field to the bot.
   Use this method to reply to shipping queries.
   On success, True is returned."
  [this content]
  (http/request this "answerShippingQuery" content))

(defn answer-precheckout-query
  "Once the user has confirmed their payment and shipping details,
   the Bot API sends the final confirmation in the form of an Update
   with the field pre_checkout_query.
   Use this method to respond to such pre-checkout queries.
   On success, True is returned."
  [this content]
  (http/request this "answerPreCheckoutQuery" content))

(def behavior
  "Map for extending the core TBot record with functions."
  {:send-invoice send-invoice
   :answer-shipping-query answer-shipping-query
   :answer-precheckout-query answer-precheckout-query})
