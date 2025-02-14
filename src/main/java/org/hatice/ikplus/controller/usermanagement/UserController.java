package org.hatice.ikplus.controller.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.userrequest.LoginRequestDto;
import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.userresponse.LoginResponseDto;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    
    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> registerUser(@RequestBody @Valid RegisterRequestDto dto) {
    if (!dto.password().equals(dto.rePassword()))
        throw new IKPlusException(ErrorType.PASSWORD_MISMATCH);
    userService.register(dto);
    
    return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                                     .code(200)
                                     .data(true)
                                     .message("Üyelik Başarıyla Oluşturuldu.")
                                     .success(true)
                                     .build());
    }
    
    @PostMapping(LOGIN)
    public ResponseEntity<BaseResponse<LoginResponseDto>> login(@RequestBody @Valid LoginRequestDto dto) {
        // UserService'ten login fonksiyonunu çağırıyoruz
        LoginResponseDto loginResponse = userService.login(dto);
        
        // Geriye token ve kullanıcı bilgilerini içeren bir response dönüyoruz
        return ResponseEntity.ok(BaseResponse.<LoginResponseDto>builder()
                                             .code(200)
                                             .data(loginResponse)  // Token ve kullanıcı bilgileri burada olacak
                                             .message("Giriş başarılı.")
                                             .success(true)
                                             .build());
    }
    
    
    
    
    @PostMapping(SAVEUSER)
    public ResponseEntity<BaseResponse<Boolean>> save(@RequestBody @Valid SaveUserRequestDto dto){
         userService.save(dto);
         return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                                   .code(200)
                                   .data(true)
                                   .message("User Başarıyla Kaydedildi.")
                                   .success(true)
                                   .build());
     }
     
     @GetMapping(GETALLUSERS)
    public ResponseEntity<BaseResponse<List<VwUser>>> getAllUsers(){
         return ResponseEntity.ok(BaseResponse.<List<VwUser>>builder()
                                          .code(200)
                                          .data(userService.getAllUsers())
                                          .message("Kullanıcılar Başarıyla Getirildi.")
                                          .success(true)
                                          .build());
     }
    
    @GetMapping(FINDBYID)
    public ResponseEntity<BaseResponse<User>> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(BaseResponse.<User>builder()
                                             .code(200)
                                             .data(user)
                                             .message("Kullanıcı Başarıyla Bulundu.")
                                             .success(true)
                                             .build());
    }
    
    
    
    
    
    
    
}