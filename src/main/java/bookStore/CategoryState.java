package bookStore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class CategoryState {
    private boolean open;
    private boolean selected;
    private boolean disabled;
}


