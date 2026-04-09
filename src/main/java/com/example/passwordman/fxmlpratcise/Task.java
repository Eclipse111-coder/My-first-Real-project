package com.example.passwordman.fxmlpratcise;

public class Task {
        private String name;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
