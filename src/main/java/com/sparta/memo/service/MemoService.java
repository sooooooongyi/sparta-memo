package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(JdbcTemplate jdbcTemplate) {
        this.memoRepository = new MemoRepository(jdbcTemplate);
    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        Memo saveMemo = memoRepository.save(memo);

        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {
        return memoRepository.findAll();
    }

    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id);
        if(memo != null) {
            memoRepository.update(id, requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(Long id) {
        Memo memo = memoRepository.findById(id);
        if(memo != null) {
            memoRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
