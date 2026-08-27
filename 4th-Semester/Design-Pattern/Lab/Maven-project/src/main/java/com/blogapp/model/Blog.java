package com.blogapp.model;

/**
 * Blog model — represents a row in the 'blogs' table.
 * authorName is populated via a JOIN with the users table.
 */
public class Blog {
    private final int    id;
    private final String title;
    private final String content;
    private final int    authorId;
    private final String authorName;
    private final String createdAt;

    public Blog(int id, String title, String content,
                int authorId, String authorName, String createdAt) {
        this.id         = id;
        this.title      = title;
        this.content    = content;
        this.authorId   = authorId;
        this.authorName = authorName;
        this.createdAt  = createdAt;
    }

    public int    getId()         { return id; }
    public String getTitle()      { return title; }
    public String getContent()    { return content; }
    public int    getAuthorId()   { return authorId; }
    public String getAuthorName() { return authorName; }
    public String getCreatedAt()  { return createdAt; }
}
