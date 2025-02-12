package org.hatice.ikplus.controller.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
     @PostMapping(Endpoints.SAVEUSER)
    public ResponseEntity<BaseResponse<Boolean>> save(@RequestBody @Valid SaveUserRequestDto dto){
         userService.save(dto);
         return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                                   .code(200)
                                   .data(true)
                                   .message("User Başarıyla Kaydedildi.")
                                   .success(true)
                                   .build());
     }
     
     @GetMapping(Endpoints.GETALLUSERS)
    public ResponseEntity<BaseResponse<List<VwUser>>> getAllUsers(){
         return ResponseEntity.ok(BaseResponse.<List<VwUser>>builder()
                                          .code(200)
                                          .data(userService.getAllUsers())
                                          .message("Kullanıcılar Başarıyla Getirildi.")
                                          .success(true)
                                          .build());
     }
    
    @GetMapping(Endpoints.FINDBYID)
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