package br.edu.utfpr.td.tsi.trabalho2apiswebservicesconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_FINANCEIRAS = "transacoes.financeiras";

    public static final String EXCHANGE_SUSPEITAS = "transacoes.suspeitas";

    public static final String QUEUE_POLICIA = "policia.federal";

    public static final String QUEUE_RECEITA = "receita.federal";

    @Bean
    public Queue queueFinanceiras() {
        return new Queue(QUEUE_FINANCEIRAS, true);
    }

    @Bean
    public FanoutExchange exchangeSuspeitas() {
        return new FanoutExchange(EXCHANGE_SUSPEITAS);
    }

    @Bean
    public Queue queuePolicia() {
        return new Queue(QUEUE_POLICIA, true);
    }

    @Bean
    public Queue queueReceita() {
        return new Queue(QUEUE_RECEITA, true);
    }

    //bindings: conectam as filas ao Exchange.
    //tudo que cair no Exchange vai para ambas as filas (Fanout).
    @Bean
    public Binding bindingPolicia(FanoutExchange exchangeSuspeitas, Queue queuePolicia) {
        return BindingBuilder.bind(queuePolicia).to(exchangeSuspeitas);
    }

    @Bean
    public Binding bindingReceita(FanoutExchange exchangeSuspeitas, Queue queueReceita) {
        return BindingBuilder.bind(queueReceita).to(exchangeSuspeitas);
    }
}
