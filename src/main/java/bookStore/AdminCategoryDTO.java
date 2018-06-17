package bookStore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class AdminCategoryDTO implements CategoryInfoHolder {
    private String id;
    private String text;
    private CategoryState state;
    private AdminCategoryDTO parent;
    private String parentCategoryId;
    @Override
    public void setId(String id) {

    }

    @Override
    public void setText(String text) {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getText() {
        return null;
    }
}
