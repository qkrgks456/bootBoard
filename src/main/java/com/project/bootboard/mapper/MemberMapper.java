package com.project.bootboard.mapper;

import com.project.bootboard.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    String idCheck(@Param("id") String id);

    int signMember(@Param("dto") MemberDto memberDto);

    MemberDto getMember(@Param("id") String memberId);

    int authInsert(@Param("dto") MemberDto memberDto);

    List<String> getAuth(@Param("id") String memberId);
}
