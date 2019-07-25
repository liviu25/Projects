package repository;

import domain.Comment;

public class CommentRepository extends AbstactRepository<Comment>  {


    public CommentRepository() {
        super();
        this.setClazz(Comment.class);
    }
}
