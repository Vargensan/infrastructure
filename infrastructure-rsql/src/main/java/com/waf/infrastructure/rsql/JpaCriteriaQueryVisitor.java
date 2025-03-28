package com.waf.infrastructure.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JpaCriteriaQueryVisitor
 * <p>
 * Visitor class for Criteria Query creation from RSQL AST Nodes.
 *
 * @author AntonioRabelo
 *
 * @param <T> Entity type
 */
@SuppressWarnings("unused")
public class JpaCriteriaQueryVisitor<T> extends AbstractJpaVisitor<CriteriaQuery<T>, T> implements RSQLVisitor<CriteriaQuery<T>, EntityManager> {

    private static final Logger LOG = Logger.getLogger(JpaCriteriaQueryVisitor.class.getName());

    private final JpaPredicateVisitor<T> predicateVisitor;

    /**
     * Construtor with template varargs for entityClass discovery.
     *
     * @param t not for usage
     */
    @SafeVarargs
    public JpaCriteriaQueryVisitor(T... t) {
        super(t);
        this.predicateVisitor = new JpaPredicateVisitor<>(t);
    }

    /**
     * Get the Predicate Visitor instance.
     *
     * @return Return the Predicate Visitor.
     */
    protected JpaPredicateVisitor<T> getPredicateVisitor() {
        this.predicateVisitor.setBuilderTools(this.getBuilderTools());
        return this.predicateVisitor;
    }

    /* (non-Javadoc)
     * @see cz.jirutka.rsql.parser.ast.RSQLVisitor#visit(cz.jirutka.rsql.parser.ast.AndNode, java.lang.Object)
     */
    public CriteriaQuery<T> visit(AndNode node, EntityManager entityManager) {
        LOG.log(Level.INFO, "Creating CriteriaQuery for AndNode: {0}", node);
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(entityClass);
        From<T, T> root = criteria.from(entityClass);
        return criteria.where(this.getPredicateVisitor().defineRoot(root).visit(node, entityManager));
    }

    /* (non-Javadoc)
     * @see cz.jirutka.rsql.parser.ast.RSQLVisitor#visit(cz.jirutka.rsql.parser.ast.OrNode, java.lang.Object)
     */
    public CriteriaQuery<T> visit(OrNode node, EntityManager entityManager) {
        LOG.log(Level.INFO, "Creating CriteriaQuery for OrNode: {0}", node);
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(entityClass);
        From<T, T> root = criteria.from(entityClass);
        return criteria.where(this.getPredicateVisitor().defineRoot(root).visit(node, entityManager));
    }

    /* (non-Javadoc)
     * @see cz.jirutka.rsql.parser.ast.RSQLVisitor#visit(cz.jirutka.rsql.parser.ast.ComparisonNode, java.lang.Object)
     */
    public CriteriaQuery<T> visit(ComparisonNode node, EntityManager entityManager) {
        LOG.log(Level.INFO, "Creating CriteriaQuery for ComparisonNode: {0}", node);
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(entityClass);
        From<T, T> root = criteria.from(entityClass);
        return criteria.where(this.getPredicateVisitor().defineRoot(root).visit(node, entityManager));
    }
}
