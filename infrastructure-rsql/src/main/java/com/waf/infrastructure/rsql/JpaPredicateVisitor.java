package com.waf.infrastructure.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JpaPredicateVisitor
 * <p>
 * Visitor class for Predicate creation from RSQL AST Nodes.
 *
 * @author AntonioRabelo
 *
 * @param <T> Entity type
 */
@SuppressWarnings("unused")
public class JpaPredicateVisitor<T> extends AbstractJpaVisitor<Predicate, T> implements RSQLVisitor<Predicate, EntityManager> {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(JpaPredicateVisitor.class.getName());

    /**
     * Root.
     */
    private From root;

    /**
     * Construtor with template varargs for entityClass discovery.
     *
     * @param t not for usage
     */
    public JpaPredicateVisitor(T... t) {
        super(t);
    }

    /**
     * Define the From node.
     * @param root From node that expressions path depends on.
     * @return Fluent interface.
     */
    public JpaPredicateVisitor<T> defineRoot(From root) {
        this.root = root;
        return this;
    }

    /* (non-Javadoc)
     * @see cz.jirutka.rsql.parser.ast.RSQLVisitor#visit(cz.jirutka.rsql.parser.ast.AndNode, java.lang.Object)
     */
    public Predicate visit(AndNode node, EntityManager entityManager) {
        LOG.log(Level.INFO, "Creating Predicate for AndNode: {0}", node);
        return PredicateBuilder.<T>createPredicate(node, root, entityClass, entityManager, getBuilderTools());
    }

    /* (non-Javadoc)
     * @see cz.jirutka.rsql.parser.ast.RSQLVisitor#visit(cz.jirutka.rsql.parser.ast.OrNode, java.lang.Object)
     */
    public Predicate visit(OrNode node, EntityManager entityManager) {
        LOG.log(Level.INFO, "Creating Predicate for OrNode: {0}", node);
        return PredicateBuilder.<T>createPredicate(node, root, entityClass, entityManager, getBuilderTools());
    }

    /* (non-Javadoc)
     * @see cz.jirutka.rsql.parser.ast.RSQLVisitor#visit(cz.jirutka.rsql.parser.ast.ComparisonNode, java.lang.Object)
     */
    public Predicate visit(ComparisonNode node, EntityManager entityManager) {
        LOG.log(Level.INFO, "Creating Predicate for ComparisonNode: {0}", node);
        return PredicateBuilder.<T>createPredicate(node, root, entityClass, entityManager, getBuilderTools());
    }
}
