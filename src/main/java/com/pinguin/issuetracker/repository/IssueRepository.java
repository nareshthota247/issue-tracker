package com.pinguin.issuetracker.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pinguin.issuetracker.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

	@Query(value = "FROM Issue issue WHERE issue.status=com.pinguin.issuetracker.enums.Status.ESTIMATED and issue.assignedWeek IS NULL ORDER BY issue.storyPoint DESC")
	public List<Issue> getAllEstimatedStories();

	@Query(value = "select sum(issue.storyPoint) from Issue issue where issue.status=com.pinguin.issuetracker.enums.Status.ESTIMATED and issue.assignedWeek is null")
	public BigInteger getSumOfEstimatedStoryPoints();

	@Query(value = "select sum(issue.storyPoint) from Issue issue where issue.status=com.pinguin.issuetracker.enums.Status.ESTIMATED and issue.assignedWeek=:weekNumber")
	public Long getEstimatedPointCountForTheWeek(@Param(value = "weekNumber") Integer weekNumber);

	@Query(value = "from Issue issue where issue.status=com.pinguin.issuetracker.enums.Status.ESTIMATED and "
			+ "(issue.storyPoint < :remainingValue or issue.storyPoint=:remainingValue) and issue.assignedWeek is null order by issue.storyPoint desc")
	public List<Issue> getStoryWithClosestPointTo(@Param(value = "remainingValue") Integer remainingPoint);

	@Query(value = "SELECT d.ID as DEVELOPER, sum(i.STORY_POINT) as point from DEVELOPER d LEFT OUTER JOIN ISSUE i on i.DEVELOPER_ID=d.ID and i.ASSIGNED_WEEK=:weekId GROUP BY d.ID ORDER by sum(i.STORY_POINT) ASC ", nativeQuery = true)
	public List<Object[]> getMostAvailableDeveloperIdForStory(@Param("weekId") Integer weekId);

	@Query(value = "from Issue issue where issue.status=com.pinguin.issuetracker.enums.Status.ESTIMATED and issue.assignedWeek is not null and issue.developerId is not null order by issue.assignedWeek")
	public List<Issue> getStoryListWithNoDeveloperAssiged();
}
