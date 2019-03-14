package com.minimal.eshop.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.minimal.eshop.dto.RoleDto;
import com.minimal.eshop.dto.UserDto;
import com.minimal.eshop.enums.RoleType;
import com.minimal.eshop.errorhandling.ErrorField;
import com.minimal.eshop.errorhandling.WrongDtoFormatException;
import com.minimal.eshop.domain.RoleJpa;
import com.minimal.eshop.domain.UserJpa;
import com.minimal.eshop.repository.UserRepository;

@Service
@SuppressWarnings("unused")
public class UserMapperImpl implements UserMapper, DtoValidator {

  @Inject
  private UserRepository userRepo;

  @Inject
  private PasswordEncoder passwordEncoder;
  
  @Override
  public UserDto getUserDtoByEmail(String email) {
    return convertJpaTodto(userRepo.getUserByEmail(email));
  }

  @Override
  public UserDto getUserDtoByEmailAndPassword(String email, String password) {
    UserJpa jpa = userRepo.getUserByEmailAndPassword(email, password);
    if (null != jpa) {
        return convertJpaTodto(jpa);
    } else {
        return null;
    }
  }

  @Override
  public UserDto convertUserTodtoByUserId(Long id) {
    UserJpa jpa = userRepo.getUserById(id);
    if (null != jpa) {
        return convertJpaTodto(jpa);
    } else {
        return null;
    }
  }

  @Override
  public UserDto saveUser(UserDto dto) {
    UserJpa jpa = new UserJpa();
    validatedto(dto);
    setSimpleFieldsFromdto(jpa, dto);
    jpa.setEnabled(true);
    UserJpa created = userRepo.saveUser(jpa);
    Set<String> roleNames = new HashSet<String>();
    dto.getRoles().stream().forEach(r -> {
      roleNames.add(r.getCode());
    });
    Set<RoleJpa> roles = convertUserNewRoleStringToRoles(created, roleNames);
    userRepo.assignRoles(roles);
    return convertUserTodtoByUserId(created.getId());
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<UserDto> dtos = new ArrayList<UserDto>();
    for (UserJpa jpa : userRepo.getAllUsers()) {
        dtos.add(convertJpaTodto(jpa));
    }
    return dtos;
  }

  @Override
  public boolean deleteUserById(Long id) {
    UserJpa jpa = userRepo.getUserById(id);
    return userRepo.deleteUser(jpa);
  }

  @Override
  public UserDto updateUser(UserDto dto) {
    UserJpa jpa = userRepo.getUserById(new Long(dto.getId()));
    validatedto(dto);
    setSimpleFieldsFromdto(jpa, dto);
    UserDto olddto = convertUserTodtoByUserId(new Long(dto.getId()));
    if (checkValidRoles(dto.getRolesAsStrings())) {
      Set<String> oldRoles = new HashSet<String>(olddto.getRolesAsStrings());
      Set<String> newRoles = new HashSet<String>(dto.getRolesAsStrings());
      if (!getOldRolesDifference(oldRoles, newRoles).isEmpty()) {
        removeRoles(new Long(dto.getId()), getOldRolesDifference(oldRoles, newRoles));
      }
      if (!getNewRolesDifference(oldRoles, newRoles).isEmpty()) {
        addRoles(new Long(dto.getId()), getNewRolesDifference(oldRoles, newRoles));
      }
    }
    return convertJpaTodto(userRepo.updateUser(jpa, checkIfPasswordChanged(dto)));
  }

  @Override
  public List<RoleDto> convertUserRolesToRoleDtos(Set<RoleJpa> roles) {
    List<RoleDto> RoleDtos = new LinkedList<RoleDto>();
    for (RoleJpa role : roles) {
      RoleDtos.add(new RoleDto().setCode(role.getRole()).setTitle(RoleType.getRoleTitleByCode(role.getRole())));
    }
    return RoleDtos;
  }

  @Override
  public void addRoles(Long userId, Set<String> roles) {
    UserJpa jpa = userRepo.getUserById(userId);
    userRepo.assignRoles(convertUserNewRoleStringToRoles(jpa, roles));
  }

  @Override
  public void removeRoles(Long userId, Set<String> roles) {
    UserJpa jpa = userRepo.getUserById(userId);
    userRepo.removeRoles(userRepo.getUserRolesByNames(jpa, roles));
  }

  @Override
  public Set<String> getRolesByEmail(String email) {
    Set<String> roles = new HashSet<String>();
    userRepo.getUserRolesByEmail(email).stream().forEach(r -> {
      roles.add(r.getRole());
    });
    return roles;
  }

  @Override
  public Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    Set<String> commonRoles = new HashSet<String>();
    commonRoles.addAll(newRoles);
    Set<String> newRoleSetDiff = new HashSet<String>();
    newRoleSetDiff.addAll(newRoles);
    commonRoles.retainAll(oldRoles);
    newRoleSetDiff.removeAll(commonRoles);
    return newRoleSetDiff;
  }

  @Override
  public Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    Set<String> commonRoles = new HashSet<String>();
    commonRoles.addAll(oldRoles);
    Set<String> oldRoleSetDiff = new HashSet<String>();
    oldRoleSetDiff.addAll(oldRoles);
    commonRoles.retainAll(newRoles);
    oldRoleSetDiff.removeAll(commonRoles);
    return oldRoleSetDiff;
  }

  @Override
  public List<ErrorField> validatedto(Serializable dto) throws WrongDtoFormatException {
    List<ErrorField> errors = new LinkedList<ErrorField>();
    UserDto UserDto = (UserDto) dto;
    if (UserDto.getRoles().size() < 1) {
      errors.add(new ErrorField("roles", "User must have at least 1 valid role"));
    }
    if (UserDto.getRoles().size() > 0 && !checkValidRoles(UserDto.getRolesAsStrings())) {
      errors.add(new ErrorField("roles", "Wrong one or more roles"));
    }
    if (errors.size() > 0) {
      throw new WrongDtoFormatException("Wrong data entered", errors);
    }
    return errors;
  }
  
  private Set<RoleJpa> convertUserNewRoleStringToRoles(UserJpa jpa, Set<String> roleNames) {
    Set<RoleJpa> roles = new HashSet<RoleJpa>();
    for (String rolename : roleNames) {
        RoleJpa jpaRole = new RoleJpa();
        jpaRole.setUser(jpa);
        jpaRole.setRole(rolename);
        roles.add(jpaRole);
    }
    return roles;
}

  private UserDto convertJpaTodto(UserJpa jpa) {
    return new UserDto()//.setPassword(jpa.getPassword())
            .setFirstName(jpa.getFirstName())
            .setLastName(jpa.getLastName())
            .setEmail(jpa.getEmail())
            .setId(null != jpa.getId() ? jpa.getId() : null)
            .setRoles(convertUserRolesToRoleDtos(jpa.getRoles())).setEnabled(jpa.getEnabled());
  }

  private Set<String> convertExistingUserRolesToStrings(Set<RoleJpa> roles) {
    Set<String> roleNames = new HashSet<String>();
    for (RoleJpa role : roles) {
      roleNames.add(role.getRole());
    }
    return roleNames;
  }

  private UserJpa setSimpleFieldsFromdto(UserJpa jpa, UserDto dto) {
    jpa.setEmail(dto.getEmail());
    if (null != dto.getPassword() && !dto.getPassword().isEmpty() && !passwordEncoder.encode(dto.getPassword()).equals(jpa.getPassword())) {
      jpa.setPassword(dto.getPassword());
    }
    jpa.setFirstName(dto.getFirstName());
    jpa.setLastName(dto.getLastName());
    jpa.setEnabled(dto.isEnabled());
    return jpa;
  }

  private boolean checkValidRoles(List<String> roles) {
    if (null == roles || 0 == roles.size()) {
      return false;
    }
    for (String role : roles) {
      if (!Arrays.asList(RoleType.values()).contains(RoleType.getRoleTypeByCode(role))) {
        return false;
      }
    }
    return true;
  }

  private boolean checkIfPasswordChanged(UserDto dto) {
    return null != dto.getPassword();
  }
  
}
