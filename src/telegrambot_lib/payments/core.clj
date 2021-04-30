(ns telegrambot-lib.payments.core
  "Telegram Payments - function implementations.
   - <https://core.telegram.org/bots/api#payments>

   Most functions are multi-arity with the following options:
   - Send all parameters in a 'content' map.
   - Send only the required parameters as simple values.
   - Send the required paraemters as simple values and then 'optional' parameters in a map."
  (:gen-class)
  (:require [telegrambot-lib.http :as http]))

(defn content-map?
  "Used throughout the multi-methods in order to check if content is a map or not."
  [_ content & _]
  (map? content))

(defn send-invoice
  "Use this method to send invoices.
   On success, the sent Message is returned.

   Required
   - this ; a bot instance
   - chat_id ; id for the target private chat
   - title ; product name
   - description ; product description
   - payload ; internal bot defined invoice payload
   - provider_token ; payments provider token
   - currency ; three letter ISO 4217 currency code
   - prices ; 'LabeledPrice' array breakdown (price, tax, discount, delivery, etc)

   Optional
   - max_tip_amount ; integer of max tip accepted. US $1.45 = 145.
   - suggested_tip_amounts ; json serialized array of integer tip amounts, 4 max suggested tips,
     positive numbers, increasing order, and must not exceed max_tip_amount.
   - start_parameter ; 'deep-linking' start param
   - provider_data ; json data about invoice, payment provider specific
   - photo_url ; product photo for the invoice
   - photo_size
   - photo_width
   - photo_height
   - need_name ; true if the user's full name is required for order
   - need_phone_number ; true if the user's phone number is required for order
   - need_email ; true if the user's email is required for order
   - need_shipping_address ; true if the user's address is required for order
   - send_phone_number_to_provider ; true to send phone number to provider
   - send_email_to_provider ; true to send email to provider
   - is_flexible ; true if final price depends on shipping method
   - disable_notification ; send message silently
   - reply_to_message_id ; id of original message if a reply
   - allow_sending_without_reply ; true to send message even if replied-to message is not found
   - reply_markup ; inline keyboard markup"
  ([this content]
   (http/request this "sendInvoice" content))

  ([this chat_id title description payload provider_token
    currency prices]
   (let [content {:chat_id chat_id
                  :title title
                  :description description
                  :payload payload
                  :provider_token provider_token
                  :currency currency
                  :prices prices}]
     (send-invoice this content)))

  ([this chat_id title description payload provider_token
    currency prices & optional]
   (let [content (merge (first optional)
                        {:chat_id chat_id
                         :title title
                         :description description
                         :payload payload
                         :provider_token provider_token
                         :currency currency
                         :prices prices})]
     (send-invoice this content))))

(defn answer-shipping-query-ok
  "If you sent an invoice requesting a shipping address
   and the parameter is_flexible was specified, the Bot API
   will send an Update with a shipping_query field to the bot.
   Use this method to reply to shipping queries.
   On success, True is returned.
   Sets 'ok' param to true.

   Required
   - this ; a bot instance
   - shipping_query_id ; id of query to be answered
   - ok ; true if delivery to address is possible (auto set by this function)
   - shipping_options ; Required if 'ok' is true, array of shipping options"
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
   Sets 'ok' param to false.

   Required
   - this ; a bot instance
   - shipping_query_id ; id of query to be answered
   - ok ; true if delivery to address is possible (auto set by this function)
   - error_message ; Required if 'ok' is false, message why order cannot complete"
  ([this content]
   (http/request this "answerShippingQuery" content))

  ([this shipping_query_id error_message]
   (let [content {:shipping_query_id shipping_query_id
                  :ok false
                  :error_message error_message}]
     (answer-shipping-query-error this content))))

(defmulti answer-precheckout-query-ok
  "Once the user has confirmed their payment and shipping details,
   the Bot API sends the final confirmation in the form of an Update
   with the field pre_checkout_query.
   Use this method to respond to such pre-checkout queries.
   On success, True is returned.
   Sets 'ok' param to true.

   Required
   - this ; a bot instance
   - pre_checkout_query_id ; id of query to be answered
   - ok ; true if all is good (in stock, etc) and bot is ready to proceed with order
          (auto set by this function)"
  content-map?)

(defmethod answer-precheckout-query-ok true
  ([this content]
   (http/request this "answerPreCheckoutQuery" content)))

(defmethod answer-precheckout-query-ok false
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
   Sets 'ok' param to false.

   Required
   - this ; a bot instance
   - pre_checkout_query_id ; id of query to be answered
   - ok ; true if all is good (in stock, etc) and bot is ready to proceed with order
          (auto set by this function)
   - error_message ; Required if 'ok' is false, message why checkout cannot proceed"
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
