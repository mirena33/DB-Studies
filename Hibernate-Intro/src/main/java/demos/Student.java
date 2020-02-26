package demos;

import lombok.*;

import java.util.Date;

@Data // generates constructors, getters, setters which do not need maintenance
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Student {
    private int id;

    @NonNull //required
    private String name;

    private Date registrationDate = new Date();
}
