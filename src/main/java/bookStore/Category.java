package bookStore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private Integer id;
    private String name;
    private Integer parentsId;

}
