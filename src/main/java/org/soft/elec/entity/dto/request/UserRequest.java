package org.soft.elec.entity.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    // firstName: không được để trống, tối đa 50 ký tự
    @NotBlank(message = "{firstname.notblank}")
    @Size(max = 50, message = "{firstname.maxsize}")
    private String firstName;

    // lastName: không được để trống, tối đa 50 ký tự
    @NotBlank(message = "{firstname.notblank}")
    @Size(max = 50, message = "{firstname.maxsize}")
    private String lastName;

    // email: không để trống, đúng định dạng email, tối đa 100 ký tự
    @NotBlank(message = "{email.notblank}")
    @Size(max = 100, message = "{email.maxsize}")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "{email.invalid}")
    private String email;

    // phone: tối đa 20 ký tự, chỉ chứa số
    @Size(max = 20, message = "{phone.maxsize}")
    @Pattern(regexp = "^[0-9]*$", message = "{phone.pattern.digits}")
    private String phone;

    // password: không để trống (khi tạo mới), tối thiểu 8 ký tự, bao gồm ký tự hoa, thường, số, ký tự đặc biệt, không chứa khoảng trắng
    @NotBlank(message = "{password.notblank}")
    @Pattern(regexp = "^[^\\s]+$", message = "{password.pattern.nospaces}")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "{password.pattern.complexity}")
    private String password;

    private boolean enabled;

    // role: không để trống, tối đa 50 ký tự
    @NotBlank(message = "{role.notblank}")
    @Size(max = 50, message = "{role.maxsize}")
    private String role;

    private String lastLogin;
}

