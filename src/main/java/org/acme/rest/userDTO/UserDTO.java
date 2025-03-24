package org.acme.rest.userDTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UserDTO {

        @NotNull
        private String nickname;
        private int health;
        private Long experience;
        private List<ItemDTO> items; // optional
        private ProfessionDTO profession; // optional
        @NotNull
        private PersonalDataDTO personalData; // required

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public Long getExperience() {
            return experience;
        }

        public void setExperience(Long experience) {
            this.experience = experience;
        }

        public List<ItemDTO> getItems() {
            return items;
        }

        public void setItems(List<ItemDTO> items) {
            this.items = items;
        }

        public ProfessionDTO getProfession() {
            return profession;
        }

        public void setProfession(ProfessionDTO profession) {
            this.profession = profession;
        }

        public PersonalDataDTO getPersonalData() {
            return personalData;
        }

        public void setPersonalData(PersonalDataDTO personalData) {
            this.personalData = personalData;
        }
}

