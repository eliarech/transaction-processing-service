package com.eliarech.transaction.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    private String id;

    @NotBlank(message = "Transaction reference cannot be blank")
    @Indexed(unique = true)
    private String reference;

    @NotBlank(message = "Account ID cannot be blank")
    private String accountId;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Currency cannot be blank")
    private String currency;

    @NotBlank(message = "Transaction type cannot be blank")
    private String type;

    private String description;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    public enum Status {
        PENDING, COMPLETED, FAILED, CANCELLED
    }
}
