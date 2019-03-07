package hio.dto.response;

import hio.model.menu.Category;

public class MenuResponseDTO {

    Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
