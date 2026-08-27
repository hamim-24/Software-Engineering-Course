package com.blogapp.dao;

import com.blogapp.model.Blog;

import java.util.List;

/**
 * BlogDao — Data Access Object interface for the 'blogs' table.
 *
 * Pattern: DAO (Repository)
 */
public interface BlogDao {

    /**
     * Returns a paginated list of all blogs, ordered by created_at DESC.
     * Each Blog has its authorName populated via a JOIN with users.
     *
     * @param page     1-based page number
     * @param pageSize number of records per page
     * @return list of blogs for the given page
     */
    List<Blog> findAllPaginated(int page, int pageSize);

    /**
     * Returns all blogs belonging to a specific author.
     *
     * @param authorId the user id of the author
     * @return list of the author's blogs
     */
    List<Blog> findByAuthorId(int authorId);

    /**
     * Returns the total number of blogs (for pagination calculation).
     *
     * @return total blog count
     */
    int countAll();
}
