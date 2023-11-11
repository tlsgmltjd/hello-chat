package com.example.hellochat.domain.Board.repository;

import com.example.hellochat.domain.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
