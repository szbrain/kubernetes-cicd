package com.koolearn.club.service;

import com.koolearn.club.dto.comment.CommentListDTO;
import com.koolearn.club.entity.PeComment;

import java.util.List;

/**
 * Created by lvyangjun on 2018/3/30.
 */

public interface ICommentService {

     PeComment getById(int id);

     Integer checkComment(Integer stuId, Integer learningId, Integer commentId, String content);

    List<CommentListDTO> listComment(int learningId);
}
