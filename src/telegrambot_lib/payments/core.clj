(ns telegrambot-lib.payments.core
  "Telegram Payments - function implementations.
   
   * https://core.telegram.org/bots/api#payments

   Most functions are multi-arity with the following options:

   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."

  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn send-invoice
  "Use this method to send invoices.
   On success, the sent Message is returned."
  ([this content]
   (http/request this "sendInvoice" content))

  ([this chat_id title description payload provider_token
    start_parameter currency prices]
   (let [content {:chat_id chat_id
                  :title title
                  :description description
                  :payload payload
                  :provider_token provider_token
                  :start_parameter start_parameter
                  :currency currency
                  :prices prices}]
     (send-invoice this content)))

  ([this chat_id title description payload provider_token
    start_parameter currency prices & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :title title
                         :description description
                         :payload payload
                         :provider_token provider_token
                         :start_parameter start_parameter
                         :currency currency
                         :prices prices})]
     (send-invoice this content))))

(defn answer-shipping-query-ok
  "If you sent an invoice requesting a shipping address
   and the parameter is_flexible was specified, the Bot API
   will send an Update with a shipping_query field to the bot.
   Use this method to reply to shipping queries.
   On success, True is returned.
   Sets 'ok' param to true."
  ([this content]
   (http/request this "answerShippingQuery" content))

  ([this shipping_query_id shipping_options]
   (let [content {:shipping_query_id shipping_query_id
                  :ok true
                  :shipping_options shipping_options}]
     (answer-shipping-query-ok this content))))

(defn answer-shipping-query-error
  "If you sent an invoice requesting a shipping address
   and the parameter is_flexible was specified, the Bot API
   will send an Update with a shipping_query field to the bot.
   Use this method to reply to shipping queries.
   On success, True is returned.
   Sets 'ok' param to false."
  ([this content]
   (http/request this "answerShippingQuery" content))

  ([this shipping_query_id error_message]
   (let [content {:shipping_query_id shipping_query_id
                  :ok false
                  :error_message error_message}]
     (answer-shipping-query-error this content))))

(defn answer-precheckout-query-ok
  "Once the user has confirmed their payment and shipping details,
   the Bot API sends the final confirmation in the form of an Update
   with the field pre_checkout_query.
   Use this method to respond to such pre-checkout queries.
   On success, True is returned.
   Sets 'ok' param to true."
  ([this content]
   (http/request this "answerPreCheckoutQuery" content))

  ([this pre_checkout_query_id]
   (let [content {:pre_checkout_query_id pre_checkout_query_id
                  :ok true}]
     (answer-precheckout-query-ok this content))))

(defn answer-precheckout-query-error
  "Once the user has confirmed their payment and shipping details,
   the Bot API sends the final confirmation in the form of an Update
   with the field pre_checkout_query.
   Use this method to respond to such pre-checkout queries.
   On success, True is returned.
   Sets 'ok' param to false."
  ([this content]
   (http/request this "answerPreCheckoutQuery" content))

  ([this pre_checkout_query_id error_message]
   (let [content {:pre_checkout_query_id pre_checkout_query_id
                  :ok false
                  :error_message error_message}]
     (answer-precheckout-query-error this content))))

(def behavior
  "Map for extending the core TBot record with functions."
  {:send-invoice send-invoice
   :answer-shipping-query-ok answer-shipping-query-ok
   :answer-shipping-query-error answer-shipping-query-error
   :answer-precheckout-query-ok answer-precheckout-query-ok
   :answer-precheckout-query-error answer-precheckout-query-error})
