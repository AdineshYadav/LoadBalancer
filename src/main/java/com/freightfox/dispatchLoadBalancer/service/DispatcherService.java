package com.freightfox.dispatchLoadBalancer.service;

import com.freightfox.dispatchLoadBalancer.dto.DispatchPlanResponseDTO;

public interface DispatcherService {
    DispatchPlanResponseDTO generateDispatchPlan();
}
