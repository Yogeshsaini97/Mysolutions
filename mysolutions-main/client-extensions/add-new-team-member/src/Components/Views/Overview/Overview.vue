<script setup>
import { inject, ref } from 'vue';
import { ChangeDateFormat } from '../../../Utils/Utils';
const ProjectGetOneData = inject("ProjectGetOneData");
const api2Data = inject("api2Data");
const no_of_Issues = inject("no_of_Issues");
const no_of_Risks = inject("no_of_Risks");
</script>


<template>
  <div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="one" role="tabpanel" aria-labelledby="one-tab">
      <div class="row">
        <div class="col-md-3 border-right">
          <h2 class="mt-4 line-height-15">Add new</h2>
          <div class="mt-4">
            <p class="text-grey">Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint
              blanditiis dignissimos ut non enim error. Dolor ut ab sit.</p>
          </div>
        </div>
        <div class="col-md-9">
          <div class="custom-form mt-3">
            <div class="row">
              <div class="col-12 col-md-6">
                <div class="form-group mb-4">
                  <label class="text-grey">Entity name</label>
                  <select class="form-control text-grey" disabled>
                    <option>Government</option>
                  </select>
                </div>
              </div>
              <div class="col-12 col-md-6">
                <div class="form-group mb-4">
                  <label class="text-grey">Department</label>
                  <select class="form-control text-grey" disabled>
                    <option>Ministry of Health</option>
                  </select>
                </div>
              </div>
              <div class="col-12 col-md-6">
                <div class="form-group mb-4">
                  <label>Full name</label>
                  <input type="text" class="form-control" placeholder="Enter Full name" value="David Elson" />
                </div>
              </div>
              <div class="col-12 col-md-6">
                <div class="form-group mb-4">
                  <label>Email address</label>
                  <input type="email" class="form-control" placeholder="Enter Email address"
                    value="muhammad.asad@moh.ksa.com" />
                </div>
              </div>
              <div class="col-12 col-md-6">
                <div class="form-group mb-4">
                  <label>Phone</label>
                  <input type="text" class="form-control" placeholder="Enter Phone" value="+966 7857126532" />
                </div>
              </div>
              <div class="col-12 col-md-6"></div>
              <div class="col-12 col-md-6">
                <div class="form-group">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="gridCheck">
                    <label class="form-check-label text-dark font-10 ml-3" for="gridCheck">
                      Dolor ut ab sit. Ut vero maiores autem culpa corrupti reiciendis aspernatur. Sint blanditiis
                      dignissimos ut non enim error. Dolor ut ab sit.
                    </label>
                  </div>
                </div>
              </div>
              <div class="col-12 col-md-12">
                <div class="mt-md-100 mb-4" style="display:flex">
                  <button type="button" class="btn btn-default w-220p">Assign role & privileges</button>
                  <button type="button" class="btn btn-light w-220p ml-3">Cancel</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="tab-pane fade" id="two" role="tabpanel" aria-labelledby="two-tab">
      ..............
    </div>
    <div class="tab-pane fade" id="three" role="tabpanel" aria-labelledby="three-tab">
      ..............
    </div>
  </div>
</template>