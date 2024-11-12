    package Capstone.fpsgame.dto;

    import Capstone.fpsgame.domain.User;
    import Capstone.fpsgame.domain.UserInfo;
    import Capstone.fpsgame.domain.Weapon;
    import lombok.AllArgsConstructor;
    import lombok.Builder;

    /*
    record같은 경우는 자동으로 final로 선언 되므로, static을 붙혀야 다음과 같이 사용이 가능함
     */
    public record SignInResponseDto(String nickname, float movementSpeed, float jumpPower, float health,
                                    String weaponName, float attackPower,int ammoCapacity,float weight) {
        public static SignInResponseDto from (User user, UserInfo userInfo, Weapon weapon){
            return new SignInResponseDto(user.getNickName(),userInfo.getMovementSpeed()
                    ,userInfo.getJumpPower(),userInfo.getHealth()
                    ,weapon.getName(),weapon.getAttackPower(),weapon.getAmmoCapacity()
                    ,weapon.getWeight());
        }
    }
