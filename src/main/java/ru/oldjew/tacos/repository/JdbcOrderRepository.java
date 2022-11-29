package ru.oldjew.tacos.repository;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.oldjew.tacos.model.Ingredient;
import ru.oldjew.tacos.model.Taco;
import ru.oldjew.tacos.model.TacoOrder;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                "insert into Taco_Order (delivery_State, delivery_City, delivery_Street, delivery_Zip, " +
                        "delivery_Name, cc_Number, cc_Expiration, cc_CVV, placed_at) " +
                        "VALUES (?,?,?,?,?,?,?,?,?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );

        pscf.setReturnGeneratedKeys(true);

        tacoOrder.setPlacedAt(new Date());

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        tacoOrder.getDeliveryState(),
                        tacoOrder.getDeliveryCity(),
                        tacoOrder.getDeliveryStreet(),
                        tacoOrder.getDeliveryZip(),
                        tacoOrder.getDeliveryName(),
                        tacoOrder.getCcNumber(),
                        tacoOrder.getCcExpiration(),
                        tacoOrder.getCcCVV(),
                        tacoOrder.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        long orderId = jdbcOperations.update(psc, keyHolder);
        tacoOrder.setId(orderId);

        List<Taco> tacos = tacoOrder.getTacos();
        int i = 0;
        for (Taco taco : tacos){
            saveTaco(orderId, i++, taco);
        }
        return tacoOrder;
    }

    private Long saveTaco(Long orderId, int orderKey, Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                    "insert into Taco (name, taco_order, taco_order_key, created_at) " +
                        "VALUES (?,?,?,?)",
                        Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        orderId,
                        orderKey,
                        taco.getCreatedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredientRefs());
        return tacoId;
    }

    private void saveIngredientRefs(Long tacoId, List<IngredientRef> ingredientRefs){
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs){
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, taco, taco_key) " +
                    "VALUES (?,?,?)",
                    ingredientRef.getIngredientId(), tacoId, key++
            );
        }
    }
}
