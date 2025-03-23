package com.waf.infrastructure.rsql;

import cz.jirutka.rsql.parser.ast.Node;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

/**
 * PredicateBuilderStrategy
 *
 * Strategy for delegate predicate creation for new operators.
 *
 * @author AntonioRabelo
 * @since 2015-02-13
 */
public interface PredicateBuilderStrategy {

    /**
     * Create a Predicate from the RSQL AST node.
     *
     * @param node       RSQL AST node.
     * @param entity  	 The main entity of the query.
     * @param manager 	 JPA EntityManager.
     * @param tools      Builder tools facade.
     * @return 			 Predicate a predicate representation of the Node.
     * @throws IllegalArgumentException When illegal arguments are found.
     */
    public <T> Predicate createPredicate(Node node, From root, Class<T> entity, EntityManager manager, BuilderTools tools) throws IllegalArgumentException;
}
