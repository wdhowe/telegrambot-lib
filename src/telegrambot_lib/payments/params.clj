(ns telegrambot-lib.payments.params
  "Telegram Payments - parameter definitions."
  (:gen-class))

(defrecord send-invoice-params
           [;; Required
            chat_id ; id for the target private chat
            title ; product name
            description ; product description
            payload ; internal bot defined invoice payload
            provider_token ; payments provider token
            start_parameter ; 'deep-linking' start param
            currency ; three letter ISO 4217 currency code
            prices ; 'LabeledPrice' array breakdown (price, tax, discount, delivery, etc)
            ;; Optional
            provider_data ; json data about invoice, payment provider specific
            photo_url ; product photo for the invoice
            photo_size
            photo_width
            photo_height
            need_name ; true if the user's full name is required for order
            need_phone_number ; true if the user's phone number is required for order
            need_email ; true if the user's email is required for order
            need_shipping_address ; true if the user's address is required for order
            send_phone_number_to_provider ; true to send phone number to provider
            send_email_to_provider ; true to send email to provider
            is_flexible ; true if final price depends on shipping method
            disable_notification ; send message silently
            reply_to_message_id ; id of original message if a reply
            reply_markup ; inline keyboard markup
            ])

(defrecord answer-shipping-query-params
           [;; Required
            shipping_query_id ; id of query to be answered
            ok ; true if delivery to address is possible
            ;; Optional
            shipping_options ; Required if 'ok' is true, array of shipping options
            error_message ; Required if 'ok' is false, message why order cannot complete
            ])

(defrecord answer-precheckout-query-params
           [;; Required
            pre_checkout_query_id ; id of query to be answered
            ok ; true if all is good (in stock, etc) and bot is ready to proceed with order
            ;; Optional
            error_message ; Required if 'ok' is false, message why checkout cannot proceed
            ])
