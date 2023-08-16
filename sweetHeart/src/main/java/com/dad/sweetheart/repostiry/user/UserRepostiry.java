package com.dad.sweetheart.repostiry.user;

import com.dad.sweetheart.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepostiry extends JpaRepository<User,Long> {

    User findByUserId(Long userId);


}
