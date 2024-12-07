package com.mockproject.givetoget.config.dataseed;

import com.mockproject.givetoget.config.dataseed.enumdata.*;
import com.mockproject.givetoget.entity.*;
import com.mockproject.givetoget.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Component
public class SeedData {
    @Autowired
    private RequestStatusRepository requestStatusRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RequestTypeRepository requestTypeRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionStatusRepository transactionStatusRepository;
    @Autowired
    private TransactionConfirmStatusRepository transactionConfirmStatusRepository;

    public void addData() throws IOException {
        if (!dataIsExists()) {
            addRole();
            addAccount();
            addRequestTypeData();
            addRequestStatusData();
            addTransactionStatusData();
            addTransactionConfirmStatusData();
            addAddressData();
            addUserInfo();
        }
    }

    private void addRole(){
        for (Role role : Role.values()) {
            if (!roleRepository.existsById(role.getId())) {
                RoleEntity entity = RoleEntity.builder()
                        .id(role.getId())
                        .name(role.getRoleName())
                        .build();
                roleRepository.save(entity);
            }
        }
    }

    private void addAccount() {
        AccountEntity account1 = AccountEntity.builder()
                .id(1)
                .email("an@gmail.com")
                .password(passwordEncoder.encode("1"))
                .role(roleRepository.findById(1).orElse(null))
                .build();
        AccountEntity account2 = AccountEntity.builder()
                .id(2)
                .email("phat@gmail.com")
                .password(passwordEncoder.encode("1"))
                .role(roleRepository.findById(1).orElse(null))
                .build();
        accountRepository.save(account1);
        accountRepository.save(account2);
    }
    private void addUserInfo() {
        UserInforEntity userInforEntity1 = UserInforEntity.builder()
                .id(1).username("User An").points(1)
                .account(accountRepository.findById(1).orElse(null)).build();
        userInfoRepository.save(userInforEntity1);

        UserInforEntity userInforEntity2 = UserInforEntity.builder()
                .id(2).username("User Phat").points(1)
                .account(accountRepository.findById(2).orElse(null)).build();
        userInfoRepository.save(userInforEntity2);
    }

    private void addRequestStatusData() {
        for (RequestStatus statusEnum : RequestStatus.values()) {
            if (!requestStatusRepository.existsById(statusEnum.getId())) {
                RequestStatusEntity entity = RequestStatusEntity.builder()
                        .idStatus(statusEnum.getId())
                        .status(statusEnum.getRequestStatus())
                        .build();
                requestStatusRepository.save(entity);
            }
        }
    }
    private void addTransactionStatusData() {
        for (TransactionStatus statusEnum : TransactionStatus.values()) {
            if (!transactionStatusRepository.existsById(statusEnum.getId())) {
                TransactionStatusEntity entity = TransactionStatusEntity.builder()
                        .id(statusEnum.getId())
                        .statusName(statusEnum.getTransactionStatus())
                        .build();
                transactionStatusRepository.save(entity);
            }
        }
    }

    private void addTransactionConfirmStatusData() {
        for (ConfirmStatus statusEnum : ConfirmStatus.values()) {
            if (!transactionConfirmStatusRepository.existsById(statusEnum.getId())) {
                TransactionConfirmStatusEntity entity = TransactionConfirmStatusEntity.builder()
                        .id(statusEnum.getId())
                        .statusName(statusEnum.getConfirmStatus())
                        .build();
                transactionConfirmStatusRepository.save(entity);
            }
        }
    }

    private void addRequestTypeData() {
        for (RequestType typeEnum : RequestType.values()) {
            if (!requestTypeRepository.existsById(typeEnum.getId())) {
                RequestTypeEntity entity = RequestTypeEntity.builder()
                        .id(typeEnum.getId())
                        .typeName(typeEnum.getRequestType())
                        .build();
                requestTypeRepository.save(entity);
            }
        }
    }

    private boolean dataIsExists() {
        long count = wardRepository.count();
        return count > 0;
    }

    private void addAddressData() throws IOException {
        Resource resource = new ClassPathResource("file/AddressData.sql");
        String sql = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        jdbcTemplate.execute(sql);
    }
}
