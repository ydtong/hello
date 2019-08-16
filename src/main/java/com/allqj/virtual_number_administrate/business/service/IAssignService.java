package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.vo.*;

public interface IAssignService {
    AssignDeptResult assignDept(AssignDeptRequest assignDeptRequest);

    CancelAssignDeptResult cancelAssignDept(CancelAssignDeptRequest cancelAssignDeptRequest);

    NumberAssignResult numberAssign(NumberAssignRequest numberAssignRequest);

    CancelNumberAssignResult cancelNumberAssign(CancelNumberAssignRequest cancelNumberAssignRequest);
}
