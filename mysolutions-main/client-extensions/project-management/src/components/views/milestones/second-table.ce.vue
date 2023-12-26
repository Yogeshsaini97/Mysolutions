<template>
  <tr :class="false ? 'milestoneDetails d-none' : 'milestoneDetails'">
    <td colspan="7">
      <div class="card border noshadow">
        <div class="card-body">
          <div class="table-responsive addScroll">
            <table class="table table-borderless font-weight-600">
              <thead>
                <tr>
                  <th>Task & ID</th>
                  <th>Task start</th>
                  <th>Task end</th>
                  <th>Duration</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Installation & device with contract<br /><span class="font-weight-400">ID -
                    </span></td>
                  <td>Mar 28th, 2023</td>
                  <td>Mar 31st, 2023</td>
                  <td>4 days</td>
                  <td class="text-end">
                    <button class="btn btn-danger px-4" type="button">Approve</button>
                    <button class="btn btn-light px-4 ms-2" type="button">Modify</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </td>
  </tr>
</template>