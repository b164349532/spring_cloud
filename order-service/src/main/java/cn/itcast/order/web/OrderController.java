package cn.itcast.order.web;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import com.itjy.clients.UserClient;
import com.itjy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
//   @Autowired
//   private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);
//        String url = "http://userservice/user/" + order.getUserId();
//        User userObject = restTemplate.getForObject(url, User.class);
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }
}
