package _9.group.gateway.APIGateway.service;

import _9.group.gateway.APIGateway.client.ListingClient;
import _9.group.gateway.APIGateway.client.UserClient;
import _9.group.gateway.APIGateway.client.response.GetListingsResponse;
import _9.group.gateway.APIGateway.client.response.ListingResponse;
import _9.group.gateway.APIGateway.dto.Listing;
import _9.group.gateway.APIGateway.dto.User;
import _9.group.gateway.APIGateway.dto.mapper.ListingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListingService {
    private final ListingClient listingClient;
    private final UserClient userClient;

    public List<Listing> getListings(
            Integer pageNum,
            Integer pageSize,
            Long userId
    ) {
        GetListingsResponse getListingsResponse = listingClient
                .getListings(pageNum, pageSize, userId);
        Set<User> users = getListingsUser(getListingsResponse);

        return getListingsResponse.getListings().stream()
                .map(listingResponse -> {
                    User user = users.stream()
                            .filter(u -> u.getId().equals(listingResponse.getUserId()))
                            .findFirst()
                            .orElse(null);
                    return ListingMapper.map(listingResponse, user);
                }).toList();
    }

    private Set<User> getListingsUser(GetListingsResponse getListingsResponse){
        Set<Long> userIds = getListingsResponse.getListings()
                .stream()
                .map(ListingResponse::getUserId)
                .collect(Collectors.toSet());
        return userIds
                .stream()
                .map(id -> userClient.getUserById(String.valueOf(id)))
                .collect(Collectors.toSet());
    }
}
