package tech.buildrun.picpay.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_wallet")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    @Column(unique = true)
    private String cpfCnpj;

    @Column(unique = true)
    private String email;

    private String password;

    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    public enum WalletType {
        USER(1, "User"), MERCHANT(2, "Merchant");

        private final int id;
        private final String description;

        WalletType(final int id, String description) {
            this.id = id;
            this.description = description;
        }

        @JsonValue
        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        @JsonCreator
        public static WalletType fromId(int id) {
            for (WalletType value : WalletType.values()) {
                if (value.id == id) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid id: " + id);
        }

    }

    public boolean isTransferAllowedForWalletType() {
        return this.walletType.equals(WalletType.USER);
    }

    public boolean isBalanceInsufficient(BigDecimal amount) {
        return this.balance.compareTo(amount) < 0;
    }

    public void debit(@DecimalMin("0.01") @NotNull BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void credit(@DecimalMin("0.01") @NotNull BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}