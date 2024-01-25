package com.sera.payapp.common;

import lombok.*;

/**
 * 여러 서비스에서 특정 membershipid 로 Validation을 수행할 때 사용하는 Task
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SubTask {
    private String membershipId;
    private String subTaskName;
    // TODO; enum 으로 리팩토링 할 것
    private String taskType; // banking, membership
    @Setter
    private String status; // success, fail, ready

//    public String getStatus() {
//        // TODO: 임시로 랜덤하게 성공/실패를 리턴하도록 구현
//        int iResult = new Random().nextInt(2);
//        return switch(iResult) {
//            case 0 ->  "success";
//            case 1 ->  "fail";
//            default -> "success";
//        };
//    }
}
