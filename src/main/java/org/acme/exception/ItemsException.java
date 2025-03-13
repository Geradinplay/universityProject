package org.acme.exception;

public class ItemsException extends Exception {

        private String message;

        public ItemsException(String message) {
            super(message);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

}
