package com.example.Exam.Management.Service.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document("test_center")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TestCenter {
    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String testCenterId;
    private String testCenterName;
    private String testCenterAddress;
    private List<Branch> branches;
}
